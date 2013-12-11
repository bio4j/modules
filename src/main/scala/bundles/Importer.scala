package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.statika._
import com.era7.bioinfo.bio4j.titan.programs._
import java.io._

object Importer {

  case object NCBITaxonomy extends ImporterBundle[
    File ::    // 1. Nodes DMP filename
    File ::    // 2. Names DMP filename
    File ::    // 3. Merged DMP filename
    File ::    // 4. Bio4j DB folder
    Boolean :: // 5. Associate Uniprot taxonomy (true/false)
    HNil
  ](new ImportNCBITaxonomyTitan())

}

object Data {

  case object NCBITaxonomy extends DataBundle(
    initDB = InitialBio4j, 
    rawData = RawData.NCBITaxonomy, 
    importer = Importer.NCBITaxonomy
  ){
    val args =
      rawData.inDataFolder("nodes.dmp") ::
      rawData.inDataFolder("names.dmp") ::
      rawData.inDataFolder("merged.dmp") ::
      initDB.folder ::
      true ::
      HNil
  }


}
