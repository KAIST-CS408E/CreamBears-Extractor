package services.xis.extractor

import java.io.InputStream
import java.util.logging.{Logger, Level}

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.hssf.extractor.ExcelExtractor

import org.apache.commons.io.IOUtils

import kr.dogfoot.hwplib.`object`.HWPFile
import kr.dogfoot.hwplib.reader.HWPReader
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor

object HWPExtractor extends GenExtractor {
  type T = HWPFile

  private[extractor] def _extract(hwp: T): String = TextExtractor.extract(hwp, tem)
  private[extractor] def toT(is: InputStream): T = HWPReader.fromInputStream(is)

  private val tem = TextExtractMethod.InsertControlTextBetweenParagraphText
}

object PDFExtractor extends GenExtractor {
  type T = PDDocument

  private[extractor] def _extract(doc: T): String = {
    val text = stripper.getText(doc);
    doc.close
    text
  }
  private[extractor] def toT(is: InputStream): T = PDDocument.load(is)

  private val stripper = new PDFTextStripper
  Logger.getLogger("org.apache.pdfbox").setLevel(Level.OFF)
}

object ImageExtractor extends GenExtractor {
  type T = Array[Byte]

  private[extractor] def _extract(arr: T): String = JImageExtractor.extract(arr)
  private[extractor] def toT(is: InputStream): T = IOUtils.toByteArray(is)

  override def extract(arr: Array[Byte]): String = _extract(arr)
}

object XLSExtractor extends GenExtractor {
  type T = POIFSFileSystem

  private[extractor] def _extract(fs: T): String =  {
    val text = new ExcelExtractor(fs).getText
    fs.close
    text
  }
  private[extractor] def toT(is: InputStream): T = new POIFSFileSystem(is)
}
