package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._
import com.era7.bioinfo.bio4j.titan.programs._
import java.io._

/* According to https://github.com/bio4j/Bio4j/raw/master/ModuleDependencies.png */
object Module {

  case object NCBITaxonomy extends ModuleBundle(API.NCBITaxonomy, Importer.NCBITaxonomy)()

  case object GITaxonomyIndex extends ModuleBundle(API.GITaxonomyIndex, Importer.GITaxonomyIndex)(
    moduleDeps = NCBITaxonomy :~: ∅
  )

  case object RefSeq extends ModuleBundle(API.RefSeq, Importer.RefSeq)()

  case object GeneOntology extends ModuleBundle(API.GeneOntology, Importer.GeneOntology)()
  case object EnzymeDB extends ModuleBundle(API.EnzymeDB, Importer.EnzymeDB)()

  // TODO: Split on separate SwissProt and TrEMBL modules (and importers)
  case object UniprotKB extends ModuleBundle(API.UniprotKB, Importer.UniprotKB)(
    moduleDeps = GeneOntology :~: EnzymeDB :~: ∅
  )

  case object UniRef extends ModuleBundle(API.UniRef, Importer.UniRef)(
    moduleDeps = UniprotKB :~: ∅
  )
  // TODO: Split on separate SwissProt and TrEMBL modules (and importers)
  case object ProteinInteractions extends ModuleBundle(API.ProteinInteractions, Importer.ProteinInteractions)(
    moduleDeps = UniprotKB :~: ∅
  )
  // TODO: Split on separate SwissProt and TrEMBL modules (and importers)
  case object IsoformSequences extends ModuleBundle(API.IsoformSequences, Importer.IsoformSequences)(
    moduleDeps = UniprotKB :~: ∅
  )

  /* This module combines all others */
  case object FullBio4j extends ModuleBundle(API.FullBio4j, Importer.Empty)(
    moduleDeps =
      NCBITaxonomy :~: 
      GITaxonomyIndex :~: 
      RefSeq :~: 
      GeneOntology :~: 
      EnzymeDB :~: 
      UniprotKB :~: 
      UniRef :~: 
      ProteinInteractions :~: 
      IsoformSequences :~: 
      ∅
  )

}
