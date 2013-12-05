package ohnosequences.bio4j.bundles

import ohnosequences.statika._

// TODO: write module-specific import code
object DataImporter {

  case object NCBITaxonomy         extends DataImporterBundle(RawData.NCBITaxonomy)
  case object UniprotSprot         extends DataImporterBundle(RawData.UniprotSprot)
  case object UniprotTrembl        extends DataImporterBundle(RawData.UniprotTrembl)
  case object UniprotSprotVarsplic extends DataImporterBundle(RawData.UniprotSprotVarsplic)
  case object Uniref100            extends DataImporterBundle(RawData.Uniref100)
  case object Uniref90             extends DataImporterBundle(RawData.Uniref90)
  case object Uniref50             extends DataImporterBundle(RawData.Uniref50)
  case object GO                   extends DataImporterBundle(RawData.GO)
  case object GITaxonomyIndex      extends DataImporterBundle(RawData.GITaxonomyIndex)
  case object ENZYME               extends DataImporterBundle(RawData.ENZYME)
  case object RefSeq               extends DataImporterBundle(RawData.RefSeq)

}
