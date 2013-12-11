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

 ### Bio4j distribution

Distribution represents a release, that was already deployed.


```scala
package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._

// TODO: a bundle with a link to the deployed `ReleaseBundle` in S3 may be with some API

```

