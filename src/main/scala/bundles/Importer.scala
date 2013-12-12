package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.statika._
import com.era7.bioinfo.bio4j.titan.programs._
import java.io._

object Importer {

  case object NCBITaxonomy extends ImporterBundle(InitialBio4j, RawData.NCBITaxonomy) {
    val program = Program.NCBITaxonomy(
      nodes         = rawData.inDataFolder("nodes.dmp"),
      names         = rawData.inDataFolder("names.dmp"),
      merged        = rawData.inDataFolder("merged.dmp"),
      db            = initDB.dbLocation,
      assocUnitprot = true
    )
  } 

}
