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

 ### Bio4j release

A release is a bundle which combines particular modules and when being applied, actually assembles
the database, which will be later represented by a `Bio4jDistribution`


```scala
package ohnosequences.bio4j.statika

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.awstools.s3._
import java.io._
```

Abstract interface:

```scala
trait AnyReleaseBundle extends AnyBundle {
```

- Where the resulting release will be uploaded

```scala
  val s3address: ObjectAddress
```

- Module which represents the content of this release

```scala
  type Module <: AnyModuleBundle
  val  module: Module
}
```

Constructor:

```scala
abstract class ReleaseBundle[
  M <: AnyModuleBundle,
  T <: HList: towerFor[M :~: ∅]#is
](val s3address: ObjectAddress, val module: M, val public: Boolean = true) 
  extends Bundle[M :~: ∅, T](module :~: ∅) with AnyReleaseBundle {

    type Module = M

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      try { 
        val s3 = S3.create() // we rely on instance role credentials
        val loader = s3.createLoadingManager()
        // TODO: controll public/private uploading (so far it's private)
        loader.uploadDirectory(s3address, module.dbLocation, recursively = true)
        success(s"Release ${name} was uploaded to ${s3address}")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }

}

```

