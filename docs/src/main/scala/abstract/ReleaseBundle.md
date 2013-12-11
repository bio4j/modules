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

 ### Bio4j release

A release is a bundle which combines particular importers and when being applied, actually assembles
the database, which will be later represented by a `Bio4jDistribution`


```scala
package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._

import com.era7.bioinfo.bio4j.titan.programs.InitBio4jTitan
```

Abstract interface:

```scala
trait AnyReleaseBundle extends AnyBundle
```

Constructor:

```scala
abstract class ReleaseBundle[
  D <: TypeSet: boundedBy[AnyModuleBundle]#is, 
  T <: HList:   towerFor[D]#is
](deps: D) extends Bundle[D,T](deps) with AnyReleaseBundle {

    override def install[D <: AnyDistribution](d: D): InstallResults =
      success("All modules are imported")

}

```

