package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._

object Release {

  case object TaxonomyBio4j extends ReleaseBundle(Module.NCBITaxonomy :~: ∅)
  case object IndexedTaxonomyBio4j extends ReleaseBundle(Module.NCBITaxonomy :~: Module.GITaxonomyIndex :~: ∅)

  case object FullBio4j extends ReleaseBundle(
    Module.NCBITaxonomy :~: 
    Module.GITaxonomyIndex :~: 
    Module.RefSeq :~: 
    Module.GeneOntology :~: 
    Module.EnzymeDB :~: 
    Module.UniprotKB :~: 
    Module.UniRef :~: 
    Module.ProteinInteractions :~: 
    Module.IsoformSequences :~: 
    ∅
  )
}
