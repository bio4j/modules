package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.statika._
import com.era7.bioinfo.bio4j.titan.programs._
import java.io._

case object InitialBio4j extends Bio4jInstanceBundle(new File("/media/ephemeral0/bio4jtitandb"))

object Importer {

  // This importer doesn't do anything, it just has a reference to initial DB
  case object Empty extends ImporterBundle(InitialBio4j, NoData) {
    val program = new AnyImporterProgram {
      def execute(): Unit = { () }
    }
  }

  case object NCBITaxonomy extends ImporterBundle(InitialBio4j, RawData.NCBITaxonomy) {
    val program = Program.NCBITaxonomy(
      nodes         = rawData.inDataFolder("nodes.dmp"),
      names         = rawData.inDataFolder("names.dmp"),
      merged        = rawData.inDataFolder("merged.dmp"),
      db            = initDB.dbLocation,
      assocUnitprot = true
    )
  }

  case object GITaxonomyIndex extends ImporterBundle(InitialBio4j, RawData.GITaxonomyIndex) {
    val program = Program.IndexNCBITaxonomyByGiId(
      table = rawData.inDataFolder("gi_taxid_nucl.dmp"),
      db    = initDB.dbLocation
    )
  }

  case object RefSeq extends ImporterBundle(InitialBio4j, RawData.RefSeq) {
    val program = Program.RefSeq(
      dataFolder = rawData.dataFolder,
      db         = initDB.dbLocation
    )
  }

  case object EnzymeDB extends ImporterBundle(InitialBio4j, RawData.EnzymeDB) {
    val program = Program.EnzymeDB(
      data = rawData.inDataFolder("enzyme.dat"),
      db   = initDB.dbLocation
    )
  }

  case object GeneOntology extends ImporterBundle(InitialBio4j, RawData.GeneOntology) {
    val program = Program.GeneOntology(
      data = rawData.inDataFolder("go.xml"),
      db   = initDB.dbLocation
    )
  }

  case object UniRef extends ImporterBundle(InitialBio4j, RawData.UniRef) {
    val program = Program.UniRef(
      data100 = rawData.inDataFolder("uniref100.xml"),
      data90  = rawData.inDataFolder("uniref90.xml"),
      data50  = rawData.inDataFolder("uniref50.xml"),
      db      = initDB.dbLocation
    )
  }

  case object UniprotKB extends ImporterBundle(InitialBio4j, RawData.UniprotKB) {
    val program = Program.UniprotKB(
      sprot  = rawData.inDataFolder("uniprot_sprot.xml"),
      trembl = rawData.inDataFolder("uniprot_trembl.xml"),
      db     = initDB.dbLocation,
      config = rawData.inDataFolder("uniprotData.xml")
    )
  }

  case object ProteinInteractions extends ImporterBundle(InitialBio4j, RawData.UniprotKB) {
    val program = Program.ProteinInteractions(
      sprot  = rawData.inDataFolder("uniprot_sprot.xml"),
      trembl = rawData.inDataFolder("uniprot_trembl.xml"),
      db     = initDB.dbLocation
    )
  }

  case object IsoformSequences extends ImporterBundle(InitialBio4j, RawData.UniprotSprotVarsplic) {
    val program = Program.IsoformSequences(
      data   = rawData.inDataFolder("uniprot_sprot_varsplic.fasta"),
      db     = initDB.dbLocation
    )
  }

}
