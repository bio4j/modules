package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import com.ohnosequences.bio4j.titan.programs._
import java.io._

object Module {

  case object NCBITaxonomy extends ModuleBundle(API.NCBITaxonomy, Importer.NCBITaxonomy)

  case object GITaxonomyIndex extends ModuleBundle(API.GITaxonomyIndex, Importer.GITaxonomyIndex)

  case object RefSeq extends ModuleBundle(API.RefSeq, Importer.RefSeq)

  case object GeneOntology extends ModuleBundle(API.GeneOntology, Importer.GeneOntology)
  case object EnzymeDB extends ModuleBundle(API.EnzymeDB, Importer.EnzymeDB)

  case object UniprotSwissProt extends ModuleBundle(API.UniprotSwissProt, Importer.UniprotSwissProt)
  case object UniprotTrEMBL    extends ModuleBundle(API.UniprotTrEMBL,    Importer.UniprotTrEMBL)
  case object UniprotKB        extends ModuleBundle(API.UniprotKB,        Importer.UniprotKB)

  case object UniRef extends ModuleBundle(API.UniRef, Importer.UniRef)

  case object ProteinInteractionsSwissProt extends ModuleBundle(API.ProteinInteractionsSwissProt, Importer.ProteinInteractionsSwissProt)
  case object ProteinInteractionsTrEMBL    extends ModuleBundle(API.ProteinInteractionsTrEMBL, Importer.ProteinInteractionsTrEMBL)
  case object ProteinInteractions extends ModuleBundle(API.ProteinInteractions, Importer.ProteinInteractions)

  case object IsoformSequences extends ModuleBundle(API.IsoformSequences, Importer.IsoformSequences)

  /* This module combines all others */
  case object FullBio4j extends ModuleBundle(API.FullBio4j, Importer.FullBio4j)

}
