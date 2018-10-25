package services.xis.extractor

import java.io.InputStream
import java.util.logging.{Logger, Level}

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.hssf.extractor.ExcelExtractor
import org.apache.poi.xssf.extractor.XSSFExcelExtractor
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.hwpf.extractor.WordExtractor
import org.apache.poi.xwpf.extractor.XWPFWordExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.sl.extractor.SlideShowExtractor
import org.apache.poi.hslf.usermodel.HSLFSlideShow
import org.apache.poi.xslf.usermodel.XMLSlideShow

import org.apache.commons.io.IOUtils

import kr.dogfoot.hwplib.`object`.HWPFile
import kr.dogfoot.hwplib.reader.HWPReader
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor

object HWPExtractor extends GenExtractor("hwp") {
  private[extractor] def _extract(is: InputStream): String =
    TextExtractor.extract(HWPReader.fromInputStream(is), tem)

  private val tem = TextExtractMethod.InsertControlTextBetweenParagraphText
}

object PDFExtractor extends GenExtractor("pdf") {
  private[extractor] def _extract(is: InputStream): String = {
    val doc = PDDocument.load(is)
    val text = stripper.getText(doc);
    doc.close
    text
  }

  private val stripper = new PDFTextStripper
  Logger.getLogger("org.apache.pdfbox").setLevel(Level.OFF)
}

object ImageExtractor extends GenExtractor("jpg", "jpeg", "png") {
  private[extractor] def _extract(is: InputStream): String =
    extract(IOUtils.toByteArray(is))

  override def extract(arr: Array[Byte]): String = JImageExtractor.extract(arr)
}

object XLSExtractor extends GenExtractor("xls") {
  private[extractor] def _extract(is: InputStream): String =  {
    val fs = new POIFSFileSystem(is)
    val text = new ExcelExtractor(fs).getText
    fs.close
    text
  }
}

object XLSXExtractor extends GenExtractor("xlsx") {
  private[extractor] def _extract(is: InputStream): String =  {
    val wb = new XSSFWorkbook(is)
    val text = new XSSFExcelExtractor(wb).getText
    wb.close
    text
  }
}

object DOCExtractor extends GenExtractor("doc") {
  private[extractor] def _extract(is: InputStream): String =  {
    val fs = new POIFSFileSystem(is)
    val text = new WordExtractor(fs).getText
    fs.close
    text
  }
}

object DOCXExtractor extends GenExtractor("docx") {
  private[extractor] def _extract(is: InputStream): String =  {
    val doc = new XWPFDocument(is)
    val text = new XWPFWordExtractor(doc).getText
    doc.close
    text
  }
}

object PPTExtractor extends GenExtractor("ppt") {
  private[extractor] def _extract(is: InputStream): String =  {
    val ss = new HSLFSlideShow(is)
    val text = new SlideShowExtractor(ss).getText
    ss.close
    text
  }
}

object PPTXExtractor extends GenExtractor("pptx") {
  private[extractor] def _extract(is: InputStream): String =  {
    val ss = new XMLSlideShow(is)
    val text = new SlideShowExtractor(ss).getText
    ss.close
    text
  }
}
