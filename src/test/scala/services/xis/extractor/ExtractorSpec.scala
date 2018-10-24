package services.xis.extractor

import java.io.{File, FileInputStream}

import org.scalatest._

import org.apache.commons.io.IOUtils

class CrawlSpec extends FlatSpec with Matchers {

  private val pdf = "res/test.pdf"
  private val pKey = "카이스트 문화행사 예매 가이드라인"
  private val pLen = 3382

  private val hwp = "res/test.hwp"
  private val hKey = "2019년 KAIST 동남아시아 봉사단 파견 안내"
  private val hLen = 1401

  test("pdf", pdf, PDFExtractor, pKey, pLen)
  test("hwp", hwp, HWPExtractor, hKey, hLen)

  def test(typ: String, name: String, e: GenExtractor, key: String, len: Int) {
    def check(text: String): Unit = {
      text.contains(key) shouldEqual true
      text.length shouldEqual len
    }

    s"$typ file" should "be read by name" in {
      check(e.extract(name))
    }

    s"$typ file" should "be read by file" in {
      check(e.extract(new File(name)))
    }

    s"$typ file" should "be read by stream" in {
      val fis = new FileInputStream(new File(name))
      check(e.extract(fis))
      fis.close
    }

    s"$typ file" should "be read by array" in {
      val fis = new FileInputStream(new File(name))
      check(e.extract(IOUtils.toByteArray(fis)))
      fis.close
    }
  }
}
