Nice.scalaProject

Nice.fatArtifactSettings

name := "bio4j-scala"

description := "bio4j-scala project"

organization := "ohnosequences"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "1.0.0",
  "ohnosequences" % "bio4j-titandb" % "0.2.0-SNAPSHOT" classifier "fat" intransitive()
)
