import AssemblyKeys._

Statika.distributionProject

name := "bio4j-ncbi-taxonomy"

description := "NCBI Taxonomy module of Bio4j"

bucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "ohnosequences" %% "bio4j-scala-lib" % "0.3.0-SNAPSHOT",
  "ohnosequences" %% "amazon-linux-ami" % "0.15.0",
  "ohnosequences" %% "aws-scala-tools" % "0.6.1",
  "ohnosequences" %% "statika-cli" % "0.17.0" % "test"
)

dependencyOverrides ++= Set(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "commons-codec" % "commons-codec" % "1.7",
  "ohnosequences" %% "aws-scala-tools" % "0.6.1-SNAPSHOT",
  "ohnosequences" %% "aws-statika" % "1.0.1",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2",
  "commons-beanutils" % "commons-beanutils" % "1.8.3",
  "commons-beanutils" % "commons-beanutils-core" % "1.8.3"
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
