### Index

+ src
  + main
    + scala
      + abstract
        + [APIBundle.scala](../abstract/APIBundle.md)
        + [Bio4jInstanceBundle.scala](../abstract/Bio4jInstanceBundle.md)
        + [DistributionBundle.scala](../abstract/DistributionBundle.md)
        + [ModuleBundle.scala](../abstract/ModuleBundle.md)
        + [RawDataBundle.scala](../abstract/RawDataBundle.md)
        + [ReleaseBundle.scala](../abstract/ReleaseBundle.md)
      + bundles
        + [API.scala](API.md)
        + [Module.scala](Module.md)
        + [RawData.scala](RawData.md)
        + [Release.scala](Release.md)
  + test
    + scala
      + [bio4j-scalaTest.scala](../../../test/scala/bio4j-scalaTest.md)

------


```scala
package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._

object Release {

  case object TaxonomyBio4j extends ReleaseBundle(Module.NCBITaxonomy :~: ∅)

  // TODO: more releases

}

```

