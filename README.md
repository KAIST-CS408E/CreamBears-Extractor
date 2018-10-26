# xIS-Extractor
## How to use
* Add the following line to your `project/Dependencies` file.
```scala
lazy val extractor = RootProject(uri("git://github.com/KAIST-CS408E/CreamBears-Extractor.git"))
```
* Modify your `build.sbt` file like the following:
```scala
lazy val root = (project in file("."))
  .dependsOn(extractor)
  .settings(...)
```
* Example
```scala
import services.xis.extractor.Extractor

val textPDF: String = Extractor.extract("test.pdf")
val textHWP: String = Extractor.extract("test.hwp")
val textDOC: String = Extractor.extract("test.doc")
val textDOCX: String = Extractor.extract("test.docx")
val textXLS: String = Extractor.extract("test.xls")
val textXLSX: String = Extractor.extract("test.xlsx")
val textPPT: String = Extractor.extract("test.ppt")
val textPPTX: String = Extractor.extract("test.pptx")
val textJPG: String = Extractor.extract("test.jpg")
val textJPEG: String = Extractor.extract("test.jpeg")
val textPNG: String = Extractor.extract("test.png")
val textZIP: String = Extractor.extract("test.zip")
```
