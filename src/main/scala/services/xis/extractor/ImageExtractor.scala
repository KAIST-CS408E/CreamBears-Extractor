package services.xis.extractor

import java.io.{File, IOException, InputStream, FileInputStream}

import org.apache.commons.io.IOUtils

object ImageExtractor extends GenExtractor {
  def extract(name: String): String = extract(new File(name))
  def extract(f: File): String = extract(new FileInputStream(f))
  def extract(is: InputStream): String = extract(IOUtils.toByteArray(is))
  def extract(arr: Array[Byte]): String = JImageExtractor.extract(arr)
}
