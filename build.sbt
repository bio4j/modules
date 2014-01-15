Nice.scalaProject


// an aggregating root project:
description := "bio4j statikated project"

bucketSuffix := "era7.com"

generateDocs := {}

publish := {}

// common for all subprojects:
organization in ThisBuild := "ohnosequences"

// subprojects:
lazy val lib = project
lazy val titandb = project dependsOn lib
lazy val distribution = project dependsOn titandb
