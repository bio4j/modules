Nice.scalaProject

name := "bio4j-scala-titandb"

description := "Abstract structure of bio4j-statika modules"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  // we use fat jar here to avoid repeating all the merging strategy settings:
  "ohnosequences" % "bio4j-titandb" % "0.2.0-SNAPSHOT" classifier "fat" intransitive()
)
