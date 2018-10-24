package services.xis.extractor

import java.io.{File, InputStream, ByteArrayInputStream}

import kr.dogfoot.hwplib.`object`.HWPFile
import kr.dogfoot.hwplib.reader.HWPReader
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor

object HWPExtractor extends GenExtractor {
  private val tem = TextExtractMethod.InsertControlTextBetweenParagraphText

  private def extract(hwp: HWPFile): String = TextExtractor.extract(hwp, tem)

  def extract(name: String): String = extract(HWPReader.fromFile(name))
  def extract(f: File): String = extract(f.getAbsolutePath)
  def extract(is: InputStream): String = extract(HWPReader.fromInputStream(is))
  def extract(arr: Array[Byte]): String = extract(new ByteArrayInputStream(arr))
}
