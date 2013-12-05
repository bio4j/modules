/* ### Data importer bundle

This bundle uploads data to S3 bucket and provides a link to the object.
*/

package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._

/* Abstract interface: */
trait AnyDataImporterBundle extends AnyBundle

/* Constructor: */
abstract class DataImporterBundle[
  RD <: AnyRawDataBundle, 
  T <: HList: towerFor[RD :~: ∅]#is
](val rawData: RD) extends Bundle[RD :~: ∅, T](rawData :~: ∅) with AnyDataImporterBundle {

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      // TODO: import data
      success("Data is imported")
    }

}
