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
  // APIs <: TypeSet: boundedBy[AnyAPIBundle]#is,
  RD   <: AnyRawDataBundle, 
  API  <: AnyAPIBundle, 
  T    <: HList: towerFor[RD :~: API :~: ∅]#is
](val rawData: RD, val api: API) 
  extends Bundle[RD :~: API :~: ∅, T](rawData :~: api :~: ∅) with AnyDataImporterBundle {

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      // TODO: import data
      success("Data is imported")
    }

}
