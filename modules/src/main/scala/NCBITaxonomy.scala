/*
package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import com.ohnosequences.bio4j.titan.programs._
import java.io._

object Inc {

  case class InitialBio4j(val dbLocation: File) extends Bundle() with AnyImportedDataBundle {
    type RawData = ∅
    val  rawData = ∅
    type ImportedData = ∅
    val  importedData = ∅

    // val dbLocation: File = new File("/media/ephemeral0/bio4jtitandb")

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      if (!dbLocation.exists) dbLocation.mkdirs
      InitBio4jTitan.main(Array(dbLocation.getAbsolutePath))
      success(s"Initialized Bio4j DB in ${dbLocation}")
    }
  }

  /* ### NCBITaxonomy */
  case class NCBITaxonomy(location: File = new File("/media/ephemeral0/bio4jtitandb")) extends ImportedDataBundle(
    rawData = RawData.NCBITaxonomy :~: ∅,
    importedData = InitialBio4j(location) :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.NCBITaxonomy(
        nodes  = RawData.NCBITaxonomy.inDataFolder("nodes.dmp"),
        names  = RawData.NCBITaxonomy.inDataFolder("names.dmp"),
        merged = RawData.NCBITaxonomy.inDataFolder("merged.dmp"),
        db     = dbLocation,
        assocUnitprot = true
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }

}
*/
