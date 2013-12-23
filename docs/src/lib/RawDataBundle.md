### Index

+ scala
  + [APIBundle.scala](APIBundle.md)
  + [Bio4jInstanceBundle.scala](Bio4jInstanceBundle.md)
  + [DistributionBundle.scala](DistributionBundle.md)
  + [ImportedDataBundle.scala](ImportedDataBundle.md)
  + [ModuleBundle.scala](ModuleBundle.md)
  + [RawDataBundle.scala](RawDataBundle.md)
  + [ReleaseBundle.scala](ReleaseBundle.md)

------

 ### Raw data bundle

This bundle just downloads raw data files and unpacks them if needed.


```scala
package ohnosequences.bio4j.statika

import ohnosequences.statika._
import java.net.URL
import java.io.File
import sys.process._
```

Abstract interface:

```scala
trait AnyRawDataBundle extends AnyBundle {
```

- Where the unpacked data is located:

```scala
  val dataFolder: File
```

- A constructor for relative file paths:

```scala
  def inDataFolder(name: String): File = new File(dataFolder, name)
}

case object NoData extends Bundle() with AnyRawDataBundle {
  val dataFolder: File = new File(".").getAbsoluteFile
}
```

Constructor:

```scala
abstract class RawDataBundle(val url: String) 
  extends Bundle() with AnyRawDataBundle {

    val archive: String = url.split("/").last

    val archType: Option[(String, Seq[String])] = Map(
        ".tar.gz" -> Seq("tar", "xvf", archive),
        ".gz" -> Seq("gunzip", archive)
      ).find{ case (ext, cmd) => archive.endsWith(ext) }

    val dataFolder: File = new File(archive.stripSuffix(archType.map(_._1).getOrElse("")))

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      if (!dataFolder.exists) dataFolder.mkdirs
      
      Seq("curl", url, "-o", archive) @@ dataFolder -&-
      (archType match { 
         case Some((ext, cmd)) => runCommand(cmd @@ dataFolder)()
         case _ => success("Not an archive")
      }) ->-
      success(s"${url} is downloaded and unpacked to ${dataFolder}")
    }

}

```

