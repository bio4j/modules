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
    new File("/media/ephemeral0/")
  )
  case object GITaxonomyIndex extends DistributionBundle(
    Bio4jRelease.GITaxonomyIndex,
    new File("/media/ephemeral0/")
  )

  case object TestTaxonomy extends Bundle(GITaxonomyIndex :~: ∅) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      {
        val neandertal = GITaxonomyIndex.nodeRetriever.getNCBITaxonByTaxId("63221")
        success("Got the " + neandertal.getScientificName + " taxon!")
      } -&- {
        val gorilla = GITaxonomyIndex.nodeRetriever.getNCBITaxonByTaxId("9595")
        success("Got the " + gorilla.getScientificName + " taxon!")
      }
    }
  }

} 

