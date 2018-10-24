import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "services.xis.extractor",
      scalaVersion := "2.12.7",
      version      := "0.1.0"
    )),
    name := "xis-extractor",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.apache.pdfbox" % "pdfbox" % "2.0.12",
    libraryDependencies += "org.apache.poi" % "poi" % "4.0.0",
    libraryDependencies += "commons-io" % "commons-io" % "2.5"
  )
