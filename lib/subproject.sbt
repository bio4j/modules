Nice.scalaProject

name := "bio4j-scala-lib"

description := "Abstract structure of bio4j-statika modules"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "1.0.0",
  "ohnosequences" %% "aws-scala-tools" % "0.5.0",
  "ohnosequences" % "bio4j-titandb" % "0.2.0"
)

dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.7",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2",
  "commons-beanutils" % "commons-beanutils" % "1.8.3",
  "commons-beanutils" % "commons-beanutils-core" % "1.8.3"
)

docsInputDir := baseDirectory.value + "/src/main/scala/"

docsOutputDir := "docs/src/lib/"
