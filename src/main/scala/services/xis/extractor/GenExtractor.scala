package services.xis.extractor

import java.io.{
  File, InputStream, FileInputStream, ByteArrayInputStream,
  IOException, FileNotFoundException
}

abstract class GenExtractor(exts: String*) {
  private[extractor] def _extract(t: InputStream): String

  def extract(name: String): String = extract(new File(name))
  def extract(f: File): String = 
    try {
      extract(new FileInputStream(f))
    } catch {
      case _: FileNotFoundException => ""
    }
  def extract(is: InputStream): String =
    try {
      _extract(is)
    } catch {
      case _: IOException => ""
    }
  def extract(a: Array[Byte]): String = extract(new ByteArrayInputStream(a))

  private val extensions: Set[String] = exts.toSet
  def matchWith(ext: String): Boolean = extensions(ext.toLowerCase)
}
