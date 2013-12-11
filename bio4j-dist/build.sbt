import AssemblyKeys._

Statika.distributionProject

name := "statika-distributions"

organization := "ohnosequences"

description := "Bio4j statika distributions"

publishMavenStyle := true

bucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "ohnosequences" %% "bio4j-scala" % "0.1.0-SNAPSHOT" classifier "fat" intransitive(),
  "ohnosequences" %% "amazon-linux-ami" % "0.14.1",
  "ohnosequences" %% "statika-cli" % "0.17.0" % "test"
)

// this will be in the next release of nice-sbt-settings
dependencyOverrides += "org.scala-lang" % "scala-compiler" % scalaVersion.value


// sorry, no docs so far, why bother with generating?
generateDocs := {}

// Running test in parallel
// testOptions in Test += Tests.Argument("-PS")

// Showing time spent on each test
testOptions in Test += Tests.Argument("-oD")
