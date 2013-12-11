### Index

+ src
  + main
    + scala
      + abstract
        + [APIBundle.scala](APIBundle.md)
        + [Bio4jInstanceBundle.scala](Bio4jInstanceBundle.md)
        + [DistributionBundle.scala](DistributionBundle.md)
        + [ModuleBundle.scala](ModuleBundle.md)
        + [RawDataBundle.scala](RawDataBundle.md)
        + [ReleaseBundle.scala](ReleaseBundle.md)
      + bundles
        + [API.scala](../bundles/API.md)
        + [Module.scala](../bundles/Module.md)
        + [RawData.scala](../bundles/RawData.md)
        + [Release.scala](../bundles/Release.md)
  + test
    + scala
      + [bio4j-scalaTest.scala](../../../test/scala/bio4j-scalaTest.md)

------

 ### Bio4j instance bundle

This bundle represents an instance of initialized Bio4j, 
to which we are going to import data modules.


```scala
package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._
import scala.collection.JavaConversions._

import com.era7.bioinfo.bio4j.titan.programs.InitBio4jTitan

abstract class Bio4jInstanceBundle(val folder: File) extends Bundle() {

  override def install[D <: AnyDistribution](d: D): InstallResults = {
    if (!folder.exists) folder.mkdirs
    InitBio4jTitan.main(Array(folder.getAbsolutePath))
    success(s"Initialized Bio4j DB in ${folder}")
  }

}

```

