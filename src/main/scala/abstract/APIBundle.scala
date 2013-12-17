/* ### Module API bundle

This bundle describes generic API for Bio4j modules
*/

package ohnosequences.bio4j.statika

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._

/* Abstract interface: */
trait AnyAPIBundle extends AnyBundle

/* Constructor: */
abstract class APIBundle[
  D <: TypeSet: boundedBy[AnyAPIBundle]#is, 
  T <: HList: towerFor[D]#is
](deps: D = ∅) extends Bundle[D, T](deps) with AnyAPIBundle
