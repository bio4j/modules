/* ### Bio4j releases */

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.awstools.s3._
import ohnosequences.bio4j.statika._
import ohnosequences.bio4j.bundles._

object Bio4jRelease {

  case object NCBITaxonomy extends ReleaseBundle(
    ObjectAddress("my.nonexisting.bucket", "bio4j/taxonomy"), 
    Module.NCBITaxonomy
  )
  case object GITaxonomyIndex extends ReleaseBundle(
    ObjectAddress("my.nonexisting.bucket", "bio4j/indexed_taxonomy"), 
    Module.GITaxonomyIndex
  )
  case object GITaxonomyNodes extends ReleaseBundle(
    ObjectAddress("my.nonexisting.bucket", "bio4j/indexed_taxonomy_gi_nodes"), 
    Module.GITaxonomyIndex
  )

} 
