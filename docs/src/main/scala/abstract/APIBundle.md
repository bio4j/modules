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

 ### Module API bundle

This bundle describes generic API for Bio4j module bundles


```scala
package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
```

Abstract interface:

```scala
trait AnyAPIBundle extends AnyBundle
```

Constructor:

```scala
abstract class APIBundle[
  D <: TypeSet: boundedBy[AnyAPIBundle]#is, 
  T <: HList:   towerFor[D]#is
](deps: D = ∅) extends Bundle[D,T](deps) with AnyAPIBundle

```

