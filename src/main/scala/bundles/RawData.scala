package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._
import sys.process._

object RawData {

  case object NCBITaxonomy extends RawDataBundle("ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz")

  case object GITaxonomyIndex extends RawDataBundle("ftp://ftp.ncbi.nih.gov/pub/taxonomy/gi_taxid_nucl.dmp.gz")


  case object UniprotSprot 
    extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot.xml.gz"){
      override val dataFolder: File = new File("uniprot")
  }
  case object UniprotTrembl 
    extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_trembl.xml.gz"){
      override val dataFolder: File = new File("uniprot")
  }
  case object Uniprot extends Bundle(UniprotSprot :~: UniprotTrembl :~: ∅) with AnyRawDataBundle {
    override val dataFolder: File = new File("uniprot")

    // We need to add an xml config file
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      val config = """
        |<uniprot_data>
        |  <keywords>true</keywords>
        |  <interpro>true</interpro>
        |  <pfam>true</pfam>
        |  <citations>true</citations>
        |  <articles>true</articles>
        |  <online_articles>true</online_articles>
        |  <thesis>true</thesis>
        |  <books>true</books>
        |  <submissions>true</submissions>
        |  <patents>true</patents>
        |  <unpublished_observations>true</unpublished_observations>
        |  <comments>true</comments>
        |  <features>true</features>
        |  <reactome>true</reactome>
        |  <isoforms>true</isoforms>
        |  <subcellular_locations>true</subcellular_locations>
        |</uniprot_data>
        |""".stripMargin

      val file = "uniprotData.xml"

      (Seq("echo", config) #> new File(file)) ->-
      success(s"Uniprot configuration was saved to ${file}")
    }
  }

  case object UniprotSprotVarsplic 
    extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot_varsplic.fasta.gz"){
      override val dataFolder: File = new File("uniprot")
  }


  case object Uniref100 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref100/uniref100.xml.gz") {
    override val dataFolder: File = new File("uniref")
  }
  case object Uniref90 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref90/uniref90.xml.gz") {
    override val dataFolder: File = new File("uniref")
  }
  case object Uniref50 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref50/uniref50.xml.gz") {
    override val dataFolder: File = new File("uniref")
  }
  case object Uniref extends Bundle(Uniref100 :~: Uniref90 :~: Uniref50 :~: ∅) with AnyRawDataBundle {
    override val dataFolder: File = new File("uniref")
  }


  case object GeneOntology extends RawDataBundle("http://archive.geneontology.org/latest-termdb/go_daily-termdb.obo-xml.gz")

  case object EnzymeDB extends RawDataBundle("ftp://ftp.expasy.org/databases/enzyme/enzyme.dat")


  case object RefSeq extends RawDataBundle("ftp://ftp.ncbi.nih.gov/refseq/release/complete/*.gbff.gz") {
    override val dataFolder = new java.io.File("refseq_data")

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      if (!dataFolder.exists) dataFolder.mkdirs

      Seq("wget", url) @@ dataFolder -&-
      "gunzip *.gz" @@ dataFolder ->-
      success(s"${url} is downloaded and unpacked to ${dataFolder}")
    }
  }

}
