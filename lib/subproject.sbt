Nice.scalaProject

name := "bio4j-scala-lib"

description := "Abstract structure of bio4j-statika modules"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "1.0.0",
  "ohnosequences" %% "aws-scala-tools" % "0.5.0",
  // we use fat jar here to avoid repeating all the merging strategy settings:
  "ohnosequences" % "bio4j-titandb" % "0.2.0" classifier "fat" intransitive()
)

dependencyOverrides ++= Set(
  // stupid java-aws-sdk depends on two versions of this:
  "commons-codec" % "commons-codec" % "1.7"
)


docsInputDir := baseDirectory.value + "/src/main/scala/"

docsOutputDir := "docs/src/lib/"
