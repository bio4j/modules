/* ### Bio4j distributions */

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.awstools.s3._
import ohnosequences.bio4j.statika._
import ohnosequences.bio4j.bundles._
import java.io.File

object Bio4jDistribution {

  case object NCBITaxonomy extends DistributionBundle(
    Bio4jRelease.NCBITaxonomy,
    destPrefix = new File("/media/ephemeral0/")
  )
  case object GITaxonomyIndex extends DistributionBundle(
    IncrementalImporter.TaxIndexIncRelease,
    destPrefix = new File("/media/ephemeral0/")
  )
  case object GITaxonomyIndex_1000_1000 extends DistributionBundle(
    Bio4jRelease.GITaxonomyIndex_1000_1000,
    destPrefix = new File("/media/ephemeral0/")
  )

} 

object IncrementalImporter {

  case object TaxIndexIncImporter extends ImportedDataBundle(
    rawData = RawData.GITaxonomyIndex :~: ∅,
    importedData = Bio4jDistribution.NCBITaxonomy :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.IndexNCBITaxonomyByGiId(
        table = RawData.GITaxonomyIndex.inDataFolder("gi_taxid_nucl.dmp"),
        db    = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }

  case object TaxIndexIncModule extends ModuleBundle(API.GITaxonomyIndex, TaxIndexIncImporter)

  case object TaxIndexIncRelease extends ReleaseBundle(
    ObjectAddress("my.nonexisting.bucket", "bio4j/indexed_taxonomy"), 
    TaxIndexIncModule
  )

  case object Bio4jIncDist extends AWSDistribution(
    metadata = new generated.metadata.Bio4jScalaDistribution(),
    ami = Bio4jAMI(6),
    members = TaxIndexIncRelease :~: ∅
  )

}
