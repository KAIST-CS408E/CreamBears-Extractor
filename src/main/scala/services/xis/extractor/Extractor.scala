package services.xis.extractor

import java.io.File

object Extractor {
  def extract(name: String): String =
    if (name.endsWith(".hwp")) HWPExtractor.extract(name)
    else if (name.endsWith(".pdf")) PDFExtractor.extract(name)
    else ""
}
