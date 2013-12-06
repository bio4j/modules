package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._

object Release {

  case object TaxonomyBio4j extends ReleaseBundle(DataImporter.NCBITaxonomy :~: ∅)

  // TODO: more releases

}
