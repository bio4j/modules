Nice.scalaProject

name := "modules"
organization := "bio4j"
description := "Abstract structure of Bio4j Statika modules"

bucketSuffix := "era7.com"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "2.0.0-M5",
  "ohnosequences" %% "aws-scala-tools" % "0.16.0",
  "bio4j" % "bio4j-titan" % "0.4.0"
)

dependencyOverrides ++= Set(
  "com.amazonaws" % "aws-java-sdk-core" % "1.10.59",
  "commons-logging" % "commons-logging" % "1.1.3",
  "commons-codec" % "commons-codec" % "1.7",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.5.3",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.5.3",
  "org.apache.httpcomponents" % "httpclient" % "4.5.1"
)
