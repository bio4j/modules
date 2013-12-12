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
  type Importer <: AnyImporterBundle
  val  importer: Importer

  /* - Dependencies on other modules */
  type ModuleDeps <: TypeSet
  val  moduleDeps: ModuleDeps

  /* By the way, module is an instance of Bio4j, and knows where it is located */
  val dbLocation = importer.initDB.dbLocation
}

/* Constructor: */
abstract class ModuleBundle[
   A <: AnyAPIBundle, 
   I <: AnyImporterBundle, 
  Ms <: TypeSet: boundedBy[AnyBio4jInstanceBundle]#is,
   T <: HList: towerFor[A :~: I :~: Ms]#is
](val api: A, val importer: I)(val moduleDeps: Ms = ∅)
 (implicit boundEvidence: ofBundles[A :~: I :~: Ms]) // stupid check
  extends Bundle[A :~: I :~: Ms, T](api :~: importer :~: moduleDeps) with AnyModuleBundle {

    type API = A
    type Importer = I
    type ModuleDeps = Ms

}
