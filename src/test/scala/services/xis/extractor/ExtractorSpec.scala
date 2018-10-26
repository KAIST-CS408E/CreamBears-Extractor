package services.xis.extractor

import java.io.{File, FileInputStream}

import org.scalatest._

import org.apache.commons.io.IOUtils

class CrawlSpec extends FlatSpec with Matchers {

  private val tests = List(
  ("PDF",
   "res/test.pdf",
   "카이스트 문화행사 예매 가이드라인",
   3382),
  ("HWP",
   "res/test.hwp",
   "2019년 KAIST 동남아시아 봉사단 파견 안내",
   1401),
  ("JPG",
   "res/test.jpg",
   "",
   0),
  ("XLSX",
   "res/test.xlsx",
   "겨울계절학기 개설과목 및 수업시간표(타교)",
   8878),
  ("DOC",
   "res/test.doc",
   "2018 Daewoong Foundation AI(Artificial Intelligence) Based Healthcare",
   3302),
  ("DOCX",
   "res/test.docx",
   "2018년도 대웅재단 AI 기반 헬스케어 분야 대학원생 연구지원 공모 안내문",
   1182),
  ("PPTX",
   "res/test.pptx",
   "Global Challenge 개요",
   1383),
  ("ZIP",
   "res/test.zip",
   "알아내고자 하는 것 또는 검증하고자 하는 가설을 명확하게 기술",
   1913),
  ("ZIP-1",
  "res/test1.zip",
   "열람 및 사본 발급이 가능한 범위는 시행규칙 제15조제1항 각 호에 해당하는",
   33261),
  ("ZIP-2",
  "res/test2.zip",
   "I hereby request the KAIST Institutional Review Board to review",
   41502))

  tests foreach test

  "Extensions" should "be proper" in {
    List("hwp", "pdf", "jpg", "jpeg", "png",
      "xls", "xlsx", "doc", "docx", "ppt", "pptx", "zip")
      .map("." + _)
      .foreach(Extractor.available(_) shouldEqual true)
  }

  def test(q: (String, String, String, Int)) = q match {
    case (typ, name, key, len) =>
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
