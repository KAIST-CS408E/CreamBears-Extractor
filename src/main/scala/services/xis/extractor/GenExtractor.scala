package services.xis.extractor

import java.io.{File, InputStream, FileInputStream, ByteArrayInputStream}

trait GenExtractor {
  type T

  private[extractor] def _extract(t: T): String
  private[extractor] def toT(is: InputStream): T

  def extract(name: String): String = extract(new File(name))
  def extract(f: File): String = extract(new FileInputStream(f))
  def extract(is: InputStream): String = _extract(toT(is))
  def extract(a: Array[Byte]): String = extract(new ByteArrayInputStream(a))
}
