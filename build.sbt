Nice.scalaProject

Nice.fatArtifactSettings

name := "bio4j-scala"

description := "bio4j-scala project"

organization := "ohnosequences"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "1.0.0",
  "ohnosequences" %% "aws-scala-tools" % "0.4.3",
  "ohnosequences" % "bio4j-titandb" % "0.2.0-SNAPSHOT" classifier "fat" intransitive()
)

dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.7",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2",
  "com.amazonaws" % "aws-java-sdk" % "1.6.5"
)
