Nice.scalaProject

organization := "bio4j"

name := "modules"

description := "Abstract structure of Bio4j Statika modules"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "1.0.0",
  "ohnosequences" %% "aws-scala-tools" % "0.6.1",
  "bio4j" % "titandb" % "0.3.1"
)

dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.7",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2",
  "com.amazonaws" % "aws-java-sdk" % "1.6.12",
  "commons-beanutils" % "commons-beanutils" % "1.8.3",
  "commons-beanutils" % "commons-beanutils-core" % "1.8.3"
)

docsInputDir := baseDirectory.value + "/src/main/scala/"

docsOutputDir := "docs/src/"
