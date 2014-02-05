import AssemblyKeys._

Statika.distributionProject

name := "bio4j-scala-titandb"

description := "Abstract structure of bio4j-statika modules"

organization := "ohnosequences"

bucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "ohnosequences" %% "amazon-linux-ami" % "0.15.0-SNAPSHOT",
  "ohnosequences" %% "aws-scala-tools" % "0.6.1-SNAPSHOT",
  "ohnosequences" %% "statika-cli" % "0.17.0" % "test"
)

dependencyOverrides ++= Set(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "commons-codec" % "commons-codec" % "1.7",
  "ohnosequences" %% "aws-scala-tools" % "0.6.1-SNAPSHOT",
  "ohnosequences" %% "aws-statika" % "1.0.1",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2"
)


docsInputDir := baseDirectory.value + "/src/main/scala/"

docsOutputDir := "docs/src/titandb/"

mergeStrategy in assembly ~= { old => {
    case PathList("META-INF", "CHANGES.txt")                      => MergeStrategy.rename
    case PathList("META-INF", "LICENSES.txt")                     => MergeStrategy.rename
    case "log4j.properties"                                       => MergeStrategy.filterDistinctLines
    case PathList("org", "apache", "commons", "collections", _*)  => MergeStrategy.first
    case x                                                        => old(x)
  }
}
