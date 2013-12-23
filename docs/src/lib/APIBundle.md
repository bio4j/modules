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

 ### Module API bundle

This bundle describes generic API for Bio4j modules


```scala
package ohnosequences.bio4j.statika

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
  T <: HList: towerFor[D]#is
](deps: D = ∅) extends Bundle[D, T](deps) with AnyAPIBundle

```

