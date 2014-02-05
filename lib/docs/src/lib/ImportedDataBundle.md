### Index

+ scala
  + [APIBundle.scala](APIBundle.md)
  + [Bio4jInstanceBundle.scala](Bio4jInstanceBundle.md)
  + [DistributionBundle.scala](DistributionBundle.md)
  + [ImportedDataBundle.scala](ImportedDataBundle.md)
  + [ImporterProgram.scala](ImporterProgram.md)
  + [ModuleBundle.scala](ModuleBundle.md)
  + [RawDataBundle.scala](RawDataBundle.md)
  + [ReleaseBundle.scala](ReleaseBundle.md)

------

 ### Bio4j module bundle

This bundle represents imported data of Bio4j DB. It's key feature is that it can depend on ohter 
imported data bundles (and optionally on some raw data bundles), so that you can preserve the order
of importing data and can easily create bundles which don't do anything by themselves, but combine 
others.


```scala
package ohnosequences.bio4j.statika

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io.File
```

Abstract interface:

```scala
trait AnyImportedDataBundle extends AnyBio4jInstanceBundle {
```

- What to import

```scala
  type RawData <: TypeSet
  val  rawData: RawData
```

- Where to import

```scala
  type InitDB <: AnyBio4jInstanceBundle
  val  initDB: InitDB
```

- Dependencies on other importers

```scala
  type ImportedData <: TypeSet
  val  importDeps: ImportedData

}
```

Constructor:

```scala
abstract class ImportedDataBundle[
  Rs <: TypeSet: boundedBy[AnyRawDataBundle]#is,
  In <: AnyBio4jInstanceBundle,                       // head
  Is <: TypeSet: boundedBy[AnyImportedDataBundle]#is, // tail
  Ds <: TypeSet,
  Tw <: HList
](val rawData: Rs = ∅, val initDB: In, val importDeps: Is = ∅)
 (implicit 
    union: UnionSets[Rs, In :~: Is]{ type Out = Ds },
    bound: ofBundles[Ds],
    tower: towerFor[Ds]#is[Tw]
 ) extends Bundle[Ds, Tw](union(rawData, initDB :~: importDeps)) with AnyImportedDataBundle {

    type RawData = Rs
    type InitDB = In
    type ImportedData = Is

    val dbLocation: File = initDB.dbLocation

}

```

