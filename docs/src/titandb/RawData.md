### Index

+ scala
  + [API.scala](API.md)
  + [Importer.scala](Importer.md)
  + [Module.scala](Module.md)
  + [Program.scala](Program.md)
  + [RawData.scala](RawData.md)

------


```scala
package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import java.io._
import sys.process._

object RawData {

  case object NCBITaxonomy extends RawDataBundle("ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz")
  case object GITaxonomyIndex extends RawDataBundle("ftp://ftp.ncbi.nih.gov/pub/taxonomy/gi_taxid_nucl.dmp.gz")

  abstract class UniprotRawData(url: String) extends RawDataBundle(url) {
    override val dataFolder: File = new File("uniprot")
    // We need to add an xml config file
    override def install[D <: AnyDistribution](d: D): InstallResults = 
      super.install(d) -&- {
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

      val file = new File(dataFolder, "uniprotData.xml")

      (Seq("echo", config) #> file) ->-
      success(s"UniprotKB configuration was saved to ${file}")
    }
  }
  case object UniprotSwissProt
    extends UniprotRawData("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot.xml.gz")
  case object UniprotTrEMBL 
    extends UniprotRawData("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_trembl.xml.gz")

  case object UniprotSprotVarsplic 
    extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot_varsplic.fasta.gz"){
      override val dataFolder: File = new File("uniprot")
  }

  case object UniRef100 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref100/uniref100.xml.gz") 
  case object UniRef90 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref90/uniref90.xml.gz") 
  case object UniRef50 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref50/uniref50.xml.gz") 

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

```

