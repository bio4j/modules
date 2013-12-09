package ohnosequences.bio4j.bundles

import ohnosequences.statika._
import com.era7.bioinfo.bio4j.titan.programs._
import java.io._

case object InitialBio4j extends Bio4jInstanceBundle(new File("bio4jtitandb"))

object Module {

  case object NCBITaxonomy
    extends ModuleBundle(
      initDB = InitialBio4j, 
      rawData = RawData.NCBITaxonomy, 
      api = API.NCBITaxonomy, 
      importer = new ImportNCBITaxonomyTitan()
    ) {
      val args = Seq(
        rawData.pathOf("nodes.dmp"),   // 1. Nodes DMP filename
        rawData.pathOf("names.dmp"),   // 2. Names DMP filename
        rawData.pathOf("merged.dmp"),  // 3. Merged DMP filename
        initDB.folder.getAbsolutePath, // 4. Bio4j DB folder
        "true"                         // 5. Associate Uniprot taxonomy (true/false)
      )
    }

  // TODO: finish other modules

  // case object GITaxonomyIndex      extends ModuleBundle(RawData.GITaxonomyIndex, API.GITaxonomyIndex)

  // case object RefSeq               extends ModuleBundle(RawData.RefSeq, API.RefSeq)

  // case object UniprotSprot         extends ModuleBundle(RawData.UniprotSprot, API.UniprotKB)
  // case object UniprotTrembl        extends ModuleBundle(RawData.UniprotTrembl, API.UniprotKB)
  // case object UniprotSprotVarsplic extends ModuleBundle(RawData.UniprotSprotVarsplic, API.UniprotKB)
  // case object Uniref100            extends ModuleBundle(RawData.Uniref100, API.UniRef100)
  // case object Uniref90             extends ModuleBundle(RawData.Uniref90, API.UniRef90)
  // case object Uniref50             extends ModuleBundle(RawData.Uniref50, API.UniRef50)
  // case object GO                   extends ModuleBundle(RawData.GO, API.GO)
  // case object ENZYME               extends ModuleBundle(RawData.ENZYME, API.ENZYME)

}
