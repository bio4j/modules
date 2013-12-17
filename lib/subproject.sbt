Nice.scalaProject

name := "bio4j-scala-lib"

description := "Abstract structure of bio4j-statika modules"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "1.0.0",
  "ohnosequences" %% "aws-scala-tools" % "0.5.0-SNAPSHOT"
)

dependencyOverrides ++= Set(
  // stupid java-aws-sdk depends on two versions of this:
  "commons-codec" % "commons-codec" % "1.7"
)
