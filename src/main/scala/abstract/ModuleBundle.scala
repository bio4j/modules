/* ### Bio4j module bundle

This bundle represents a module of Bio4j DB. A distribution of Bio4j should depend on a number of 
such modules and that will define which data will be imported to the DB.
*/

package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._

/* Abstract interface: */
trait AnyModuleBundle extends AnyBio4jInstanceBundle {
  /* - Corresponding API of the module (it has it's own dependencies) */
  type API  <: AnyAPIBundle
  val  api: API

  /* - Corresponding data importer bundle */
  type ImportedData <: AnyImportedDataBundle
  val  importedData: ImportedData

  /* By the way, module is an instance of Bio4j, and knows where it is located */
  val dbLocation = importedData.dbLocation
}

/* Constructor: */
abstract class ModuleBundle[
   A <: AnyAPIBundle, 
   I <: AnyImportedDataBundle, 
   T <: HList: towerFor[A :~: I :~: ∅]#is
](val api: A, val importedData: I)
  extends Bundle[A :~: I :~: ∅, T](api :~: importedData :~: ∅) with AnyModuleBundle {

    type API = A
    type ImportedData = I

}
