package services.xis.extractor

import java.io.{
  File, InputStream, FileInputStream, ByteArrayInputStream,
  IOException, FileNotFoundException
}

trait GenExtractor {
  type T

  private[extractor] def _extract(t: T): String
  private[extractor] def toT(is: InputStream): T
  private[extractor] val extensions: Set[String]

  def extract(name: String): String = extract(new File(name))
  def extract(f: File): String = 
    try {
      extract(new FileInputStream(f))
    } catch {
      case _: FileNotFoundException => ""
    }
  def extract(is: InputStream): String =
    try {
      _extract(toT(is))
    } catch {
      case _: IOException => ""
    }
  def extract(a: Array[Byte]): String = extract(new ByteArrayInputStream(a))

  def matchWith(ext: String): Boolean = extensions(ext.toLowerCase)
}
