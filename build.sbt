import ohnosequences.sbt._

Nice.scalaProject

organization := "bio4j"

name := "modules"

// description := "Abstract structure of Bio4j Statika modules"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika" % "1.0.0",
  "ohnosequences" %% "aws-scala-tools" % "0.6.1",
  "bio4j" % "titandb" % "0.4.0-SNAPSHOT"
)

dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.7",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2",
  "com.amazonaws" % "aws-java-sdk" % "1.6.12",
  "commons-beanutils" % "commons-beanutils" % "1.8.3",
  "commons-beanutils" % "commons-beanutils-core" % "1.8.3"
)

docsInputDir := baseDirectory.value + "/src/main/scala/"

docsOutputDir := "docs/src/"

// credentials += Credentials("Foo realm", "foo.host.org", "usr", "pwd")

// lazy val foo = taskKey[S3Resolver => Resolver]("")

// foo := { s3resolver =>
//   val creds: S3Credentials = Credentials.forHost(credentials.value, s3resolver.url) match {
//     case Some(cs) => (cs.userName, cs.passwd)
//     case _ => sys.error("Failed to get credentials for "+s3resolver)
//   }
//   s3resolver.toSbtResolver(creds)
// }

// resolvers += foo.value(S3Resolver("Bar", "foo.host.org"))
