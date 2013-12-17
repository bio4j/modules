Nice.scalaProject

// an aggregating root project:
description := "bio4j statikated project"

publish := {}

generateDocs := {}

// common for all subprojects:
organization in ThisBuild := "ohnosequences"

// subprojects:
lazy val lib = project
lazy val titandb = project dependsOn lib
lazy val dist = project dependsOn titandb
