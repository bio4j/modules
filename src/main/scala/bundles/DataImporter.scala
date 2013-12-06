package ohnosequences.bio4j.bundles

import ohnosequences.statika._

// TODO: write module-specific import code
object DataImporter {

  case object NCBITaxonomy         extends DataImporterBundle(RawData.NCBITaxonomy, API.NCBITaxonomy)
  case object UniprotSprot         extends DataImporterBundle(RawData.UniprotSprot, API.UniprotKB)
  case object UniprotTrembl        extends DataImporterBundle(RawData.UniprotTrembl, API.UniprotKB)
  case object UniprotSprotVarsplic extends DataImporterBundle(RawData.UniprotSprotVarsplic, API.UniprotKB)
  case object Uniref100            extends DataImporterBundle(RawData.Uniref100, API.UniRef100)
  case object Uniref90             extends DataImporterBundle(RawData.Uniref90, API.UniRef90)
  case object Uniref50             extends DataImporterBundle(RawData.Uniref50, API.UniRef50)
  case object GO                   extends DataImporterBundle(RawData.GO, API.GO)
  case object GITaxonomyIndex      extends DataImporterBundle(RawData.GITaxonomyIndex, API.GITaxonomyIndex)
  case object ENZYME               extends DataImporterBundle(RawData.ENZYME, API.ENZYME)
  case object RefSeq               extends DataImporterBundle(RawData.RefSeq, API.RefSeq)

}
