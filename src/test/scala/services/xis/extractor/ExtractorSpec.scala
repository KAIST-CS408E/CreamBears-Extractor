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

  private val img = "res/test.jpg"
  private val iKey = ""
  private val iLen = 0

  private val xlsx = "res/test.xlsx"
  private val xxKey = "겨울계절학기 개설과목 및 수업시간표(타교)"
  private val xxLen = 8878

  private val doc = "res/test.doc"
  private val dKey = "2018 Daewoong Foundation AI(Artificial Intelligence) Based Healthcare"
  private val dLen = 3302

  private val docx = "res/test.docx"
  private val dxKey = "2018년도 대웅재단 AI 기반 헬스케어 분야 대학원생 연구지원 공모 안내문"
  private val dxLen = 1182

  test("PDF", pdf, pKey, pLen)
  test("HWP", hwp, hKey, hLen)
  test("Image", img, iKey, iLen)
  test("XLSX", xlsx, xxKey, xxLen)
  test("DOC", doc, dKey, dLen)
  test("DOCX", docx, dxKey, dxLen)

  def test(typ: String, name: String, key: String, len: Int) {
    def check(text: String): Unit = {
      text.contains(key) shouldEqual true
      text.length shouldEqual len
    }

    s"$typ file" should "be read by name" in {
      check(Extractor.extract(name))
    }

    s"$typ file" should "be read by file" in {
      check(Extractor.extract(new File(name)))
    }

    s"$typ file" should "be read by stream" in {
      val fis = new FileInputStream(new File(name))
      check(Extractor.extract(name, fis))
      fis.close
    }

    s"$typ file" should "be read by array" in {
      val fis = new FileInputStream(new File(name))
      check(Extractor.extract(name, IOUtils.toByteArray(fis)))
      fis.close
    }
  }
}
