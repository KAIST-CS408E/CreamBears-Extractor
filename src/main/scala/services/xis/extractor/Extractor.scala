package services.xis.extractor

import java.io.{File, InputStream}

object Extractor {
  private val extractors = List(
    HWPExtractor,
    PDFExtractor,
    ImageExtractor,
    XLSExtractor,
    XLSXExtractor,
    DOCExtractor,
    DOCXExtractor
  )
  private val extensions: Set[String] =
    extractors.flatMap(_.extensions).toSet

  private def extensionOf(name: String): String = {
    val i = name.lastIndexOf(".")
    if (i == -1) "" else name.substring(i + 1)
  }

  private def _extract(name: String, f: GenExtractor => String): String =
    try {
      extractors
        .filter(_.matchWith(extensionOf(name)))
        .map(f)
        .mkString("")
    } catch {
      case _: Exception => ""
    }

  def extract(name: String): String =
    _extract(name, _.extract(name))
  def extract(f: File): String =
    _extract(f.getName, _.extract(f))
  def extract(name: String, is: InputStream): String =
    _extract(name, _.extract(is))
  def extract(name: String, arr: Array[Byte]): String =
    _extract(name, _.extract(arr))

  def available(name: String): Boolean =
    extensions(extensionOf(name).toLowerCase)
}
