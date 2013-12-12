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

 ### Bio4j module bundle

This bundle represents a module of Bio4j DB. A distribution of Bio4j should depend on a number of 
such modules and that will define which data will be imported to the DB.


```scala
package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._
import scala.collection.JavaConversions._

import com.era7.bioinfo.bioinfoutil.Executable
```

Abstract interface:

```scala
trait AnyModuleBundle extends AnyBundle {
  type RawData <: AnyRawDataBundle
  val rawData: RawData

  type API  <: AnyAPIBundle
  val api: API

  // TODO: create a bundle representing importer with an HList record of required args
  type Importer <: Executable
  val importer: Importer

  // Arguments for the importer program
  val args: Seq[String]
}
```

Constructor:

```scala
abstract class ModuleBundle[
  D <: AnyRawDataBundle, 
  A <: AnyAPIBundle, 
  B <: Bio4jInstanceBundle,
  T <: HList: towerFor[B :~: D :~: A :~: ∅]#is,
  I <: Executable
](val initDB: B, val rawData: D, val api: A, val importer: I) 
  extends Bundle[B :~: D :~: A :~: ∅, T](initDB :~: rawData :~: api :~: ∅) with AnyModuleBundle {
    type RawData = D 
    type API = A
    type Importer = I

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      try { 
        importer.execute(new java.util.ArrayList(args))
        success(s"Data for the ${name} module is imported")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }

}

```
