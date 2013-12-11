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
trait AnyModuleBundle extends AnyBundle {
  type API  <: AnyAPIBundle
  val  api: API

  type Data <: AnyDataBundle
  val  data: Data
}

/* Constructor: */
abstract class ModuleBundle[
   A <: AnyAPIBundle, 
   D <: AnyDataBundle, 
  // Ms <: TypeSet: boundedBy[AnyModuleBundle]#is
   T <: HList: towerFor[A :~: D :~: ∅]#is
](val api: A, val data: D) 
  extends Bundle[A :~: D :~: ∅, T](api :~: data :~: ∅) with AnyModuleBundle {

    type API = A
    type Data = D

}
