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
    new File("/media/ephemeral0/bio4j/taxonomy/")
  )
  case object GITaxonomyIndex extends DistributionBundle(
    Bio4jRelease.GITaxonomyIndex,
    new File("/media/ephemeral0/bio4j/indexed_taxonomy/")
  )

  case object TestTaxonomy extends Bundle(GITaxonomyIndex :~: ∅) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      try { 
        val human = GITaxonomyIndex.nodeRetriever.getNCBITaxonByTaxId("9606")
        println("human.getName: " + human.getName)
        println("human.getScientificName: " + human.getScientificName)
        println("human.getComments: " + human.getComments)
        success("Got the Human taxon!")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }
  }

} 

