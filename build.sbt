Nice.scalaProject

name := "bio4j-scala"

description := "bio4j-scala project"

organization := "ohnosequences"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "1.0.0",
  "ohnosequences" % "bio4j-titandb" % "0.1.0",
  "ohnosequences" % "bioinfo-util" % "1.2.0"
)

dependencyOverrides += "commons-codec" % "commons-codec" % "1.7"
