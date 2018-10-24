#xIS-Extractor
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
import services.xis.extractor.{PDFExtractor, HWPExtractor}

val textPDF: String = PDFExtractor.extract("test.pdf")
val textHWP: String = HWPExtractor.extract("test.hwp")
```
