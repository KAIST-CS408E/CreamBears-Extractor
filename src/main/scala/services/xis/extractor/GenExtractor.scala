package services.xis.extractor

import java.io.{File, InputStream, FileInputStream, ByteArrayInputStream}

abstract class GenExtractor(exts: String*) {
  private[extractor] def _extract(is: InputStream): String

  private def __extract(is: InputStream): String =
    try {
      _extract(is)
    } catch {
      case _: Exception => ""
    }

  def extract(name: String): String = extract(new File(name))
  def extract(f: File): String = extract(new FileInputStream(f))
  def extract(is: InputStream): String = __extract(is)
  def extract(a: Array[Byte]): String = extract(new ByteArrayInputStream(a))

  private val extensions: Set[String] = exts.toSet
  def matchWith(ext: String): Boolean = extensions(ext.toLowerCase)
}
