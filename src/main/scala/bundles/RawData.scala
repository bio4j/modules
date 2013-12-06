package ohnosequences.bio4j.bundles

import ohnosequences.statika._

object RawData {

  case object NCBITaxonomy extends RawDataBundle("ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz")

/*
  case object UniprotSprot extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot.xml.gz")

  case object UniprotTrembl extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_trembl.xml.gz")

  case object UniprotSprotVarsplic extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot_varsplic.fasta.gz")

  case object Uniref100 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref100/uniref100.xml.gz")

  case object Uniref90 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref90/uniref90.xml.gz")

  case object Uniref50 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref50/uniref50.xml.gz")

  case object GO extends RawDataBundle("http://archive.geneontology.org/latest-termdb/go_daily-termdb.obo-xml.gz")

  case object GITaxonomyIndex extends RawDataBundle("ftp://ftp.ncbi.nih.gov/pub/taxonomy/gi_taxid_nucl.dmp.gz")

  case object ENZYME extends RawDataBundle("ftp://ftp.expasy.org/databases/enzyme/enzyme.dat")

  case object RefSeq extends RawDataBundle("ftp://ftp.ncbi.nih.gov/refseq/release/complete/*.gbff.gz") {
    override val dataFolder = new java.io.File("refseq_data")

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      if (!dataFolder.exists) dataFolder.mkdirs

      Seq("wget", url) @@ dataFolder -&-
      "gunzip *.gz" @@ dataFolder ->-
      success(s"${url} is downloaded and unpacked to ${dataFolder}")
    }
  }
*/*/

}
