### Index

+ src
  + main
    + scala
      + abstract
        + [APIBundle.scala](../abstract/APIBundle.md)
        + [Bio4jInstanceBundle.scala](../abstract/Bio4jInstanceBundle.md)
        + [DistributionBundle.scala](../abstract/DistributionBundle.md)
        + [ModuleBundle.scala](../abstract/ModuleBundle.md)
        + [RawDataBundle.scala](../abstract/RawDataBundle.md)
        + [ReleaseBundle.scala](../abstract/ReleaseBundle.md)
      + bundles
        + [API.scala](API.md)
        + [Module.scala](Module.md)
        + [RawData.scala](RawData.md)
        + [Release.scala](Release.md)
  + test
    + scala
      + [bio4j-scalaTest.scala](../../../test/scala/bio4j-scalaTest.md)

------


```scala
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

```

