package services.xis.extractor

import java.io.{File, IOException, InputStream}

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object PDFExtractor extends GenExtractor {
  java.util.logging.Logger
    .getLogger("org.apache.pdfbox").setLevel(java.util.logging.Level.OFF);

  private val stripper = new PDFTextStripper

  private def extract(doc: PDDocument): String = {
    val text = stripper.getText(doc);
    doc.close
    text
  }

  def extract(name: String): String =
    try {
      extract(new File(name))
    } catch {
      case _: IOException => ""
    }
  def extract(f: File): String = extract(PDDocument.load(f))
  def extract(is: InputStream): String = extract(PDDocument.load(is))
  def extract(arr: Array[Byte]): String = extract(PDDocument.load(arr))
}
