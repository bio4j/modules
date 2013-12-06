package ohnosequences.bio4j.bundles

import ohnosequences.statika._
import com.era7.bioinfo.bio4j.titan.programs._
import java.io._

// TODO: write module-specific import code
object DataImporter {

  case object NCBITaxonomy
    extends DataImporterBundle(RawData.NCBITaxonomy, API.NCBITaxonomy)(new ImportNCBITaxonomyTitan()) {
      val args = Seq(
            rawData.pathOf("nodes.dmp"),  // 1. Nodes DMP filename
            rawData.pathOf("names.dmp"),  // 2. Names DMP filename
            rawData.pathOf("merged.dmp"), // 3. Merged DMP filename
            "bio4j_titan",                // 4. Bio4j DB folder
            "true"                        // 5. Associate Uniprot taxonomy (true/false)
          )
    }

  // case object UniprotSprot         extends DataImporterBundle(RawData.UniprotSprot, API.UniprotKB)
  // case object UniprotTrembl        extends DataImporterBundle(RawData.UniprotTrembl, API.UniprotKB)
  // case object UniprotSprotVarsplic extends DataImporterBundle(RawData.UniprotSprotVarsplic, API.UniprotKB)
  // case object Uniref100            extends DataImporterBundle(RawData.Uniref100, API.UniRef100)
  // case object Uniref90             extends DataImporterBundle(RawData.Uniref90, API.UniRef90)
  // case object Uniref50             extends DataImporterBundle(RawData.Uniref50, API.UniRef50)
  // case object GO                   extends DataImporterBundle(RawData.GO, API.GO)
  // case object GITaxonomyIndex      extends DataImporterBundle(RawData.GITaxonomyIndex, API.GITaxonomyIndex)
  // case object ENZYME               extends DataImporterBundle(RawData.ENZYME, API.ENZYME)
  // case object RefSeq               extends DataImporterBundle(RawData.RefSeq, API.RefSeq)

}
