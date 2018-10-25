package services.xis.extractor

import java.io.{File, InputStream, FileInputStream, ByteArrayInputStream}

abstract class GenExtractor(exts: String*) {
  private[extractor] def _extract(is: InputStream): String

  def extract(name: String): String = extract(new File(name))
  def extract(f: File): String = extract(new FileInputStream(f))
  def extract(is: InputStream): String = _extract(is)
  def extract(a: Array[Byte]): String = extract(new ByteArrayInputStream(a))

  private[extractor] val extensions: Set[String] = exts.toSet
  def matchWith(ext: String): Boolean = extensions(ext.toLowerCase)
}
