/* ### Bio4j releases */

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.awstools.s3._
import ohnosequences.bio4j.bundles._

object Release {

  case object TaxonomyBio4j extends ReleaseBundle(
    ObjectAddress("my.nonexisting.bucket", "bio4j/taxonomy"), 
    Module.NCBITaxonomy
  )
  case object IndexedTaxonomyBio4j extends ReleaseBundle(
    ObjectAddress("my.nonexisting.bucket", "bio4j/indexed_taxonomy"), 
    Module.GITaxonomyIndex
  )
  case object FullBio4j extends ReleaseBundle(
    ObjectAddress("my.nonexisting.bucket", "bio4j/full"), 
    Module.FullBio4j
  )

} 
