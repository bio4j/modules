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

 ### Bio4j instance bundle

This bundle represents an instance of initialized Bio4j, into which we are going to import data modules.


```scala
package ohnosequences.bio4j.statika

import ohnosequences.statika._
import java.io._
```

Any Bio4j instance knows where it is

```scala
trait AnyBio4jInstanceBundle extends AnyBundle {
  val dbLocation: File
}

```

