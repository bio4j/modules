/* ### Bio4j distributions */

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.awstools.s3._
import ohnosequences.bio4j.statika._
import ohnosequences.bio4j.bundles._
import java.io.File

object Bio4jDistribution {

  case object NCBITaxonomy extends DistributionBundle(
    Bio4jRelease.NCBITaxonomy,
    destPrefix = new File("/media/ephemeral0/")
  )
  case object GITaxonomyIndex extends DistributionBundle(
    Bio4jRelease.GITaxonomyIndex,
    destPrefix = new File("/media/ephemeral0/")
  )
  case object GITaxonomyIndex_1000_1000 extends DistributionBundle(
    Bio4jRelease.GITaxonomyIndex_1000_1000,
    destPrefix = new File("/media/ephemeral0/")
  )

} 

