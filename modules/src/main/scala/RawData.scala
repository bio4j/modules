package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import java.io._
import sys.process._

object RawData {

case object UniprotSprotVarsplic 
  extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot_varsplic.fasta.gz"){
    override val dataFolder: File = new File("uniprot")
}
  case object UniRef100 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref100/uniref100.xml.gz") 
  case object UniRef90 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref90/uniref90.xml.gz") 
  case object UniRef50 extends RawDataBundle("ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref50/uniref50.xml.gz") 

}
