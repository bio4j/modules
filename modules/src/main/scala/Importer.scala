package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import com.ohnosequences.bio4j.titan.programs._
import java.io._

/* According to https://github.com/bio4j/Bio4j/raw/master/ModuleDependencies.png */
object Importer {


  /* ### UniRef */
  case object UniRef extends ImportedDataBundle(
    rawData = RawData.UniRef100 :~: RawData.UniRef90 :~: RawData.UniRef50 :~: ∅,
    initDB = UniprotKB
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.UniRef(
        data100 = RawData.UniRef100.inDataFolder("uniref100.xml"),
        data90  = RawData.UniRef90.inDataFolder("uniref90.xml"),
        data50  = RawData.UniRef50.inDataFolder("uniref50.xml"),
        db = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }

  /* ### ProteinInteractions */
  case object ProteinInteractionsSwissProt extends ImportedDataBundle(
    rawData = RawData.UniprotSwissProt :~: ∅,
    initDB = UniprotSwissProt
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.ProteinInteractions(
        data = RawData.UniprotSwissProt.inDataFolder("uniprot_sprot.xml"),
        db   = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }

  case object ProteinInteractionsTrEMBL extends ImportedDataBundle(
    rawData = RawData.UniprotTrEMBL :~: ∅,
    initDB = UniprotTrEMBL
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.ProteinInteractions(
        data = RawData.UniprotTrEMBL.inDataFolder("uniprot_trembl.xml"),
        db   = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }

  /* Both things together: */
  case object ProteinInteractions extends ImportedDataBundle(
    initDB = ProteinInteractionsSwissProt,
    importDeps = ProteinInteractionsTrEMBL :~: ∅
  )

  /* ### IsoformSequences */
  case object IsoformSequences extends ImportedDataBundle(
    rawData = RawData.UniprotSprotVarsplic :~: ∅,
    initDB = UniprotKB
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.IsoformSequences(
        data = RawData.UniprotSprotVarsplic.inDataFolder("uniprot_sprot_varsplic.fasta"),
        db   = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }


  /* ### Everything together */
  case object FullBio4j extends ImportedDataBundle(
    initDB = NCBITaxonomy,
    importDeps =
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
