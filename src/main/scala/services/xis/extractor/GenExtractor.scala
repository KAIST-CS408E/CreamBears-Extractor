package services.xis.extractor

import java.io.{File, InputStream}

trait GenExtractor {
  def extract(name: String): String
  def extract(f: File): String
  def extract(is: InputStream): String
  def extract(arr: Array[Byte]): String
}
