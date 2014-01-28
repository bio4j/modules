import AssemblyKeys._

Statika.distributionProject

name := "bio4j-scala-distribution"

description := "Bio4j statika distributions"

// publishMavenStyle := true

bucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "ohnosequences" %% "amazon-linux-ami" % "0.15.0-SNAPSHOT",
  "ohnosequences" %% "aws-scala-tools" % "0.5.5-SNAPSHOT",
  "ohnosequences" %% "statika-cli" % "0.17.0" % "test"
)

dependencyOverrides ++= Set(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "commons-codec" % "commons-codec" % "1.7",
  "com.amazonaws" % "aws-java-sdk" % "1.6.8",
  "ohnosequences" %% "aws-scala-tools" % "0.5.5-SNAPSHOT",
  "ohnosequences" %% "aws-statika" % "1.0.1",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2"
)

mergeStrategy in assembly ~= { old => {
    case PathList("META-INF", "CHANGES.txt")                      => MergeStrategy.rename
    case PathList("META-INF", "LICENSES.txt")                     => MergeStrategy.rename
    case "log4j.properties"                                       => MergeStrategy.filterDistinctLines
    case PathList("org", "apache", "commons", "collections", _*)  => MergeStrategy.first
    case x                                                        => old(x)
  }
}

// sorry, no docs so far, why bother with generating?
generateDocs := {}

// Running test in parallel
// testOptions in Test += Tests.Argument("-PS")

// Showing time spent on each test
testOptions in Test += Tests.Argument("-oD")

// publishing also a fat artifact in test:

Project.inConfig(Test)(assemblySettings)

jarName in (Test, assembly) := s"${name.value}-assembly-test-${version.value}.jar"

mergeStrategy in (Test, assembly) := (mergeStrategy in assembly).value

test in (Test, assembly) := {}

artifact in (Test, assembly) ~= { _.copy(`classifier` = Some("test")) }

addArtifact(artifact in (Test, assembly), assembly in Test)
