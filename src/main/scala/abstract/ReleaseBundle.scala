/* ### Bio4j release

A release is a bundle which combines particular importers and when being applied, actually assembles
the database, which will be later represented by a `Bio4jDistribution`
*/

package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._

/* Abstract interface: */
trait AnyReleaseBundle extends AnyBundle

/* Constructor: */
abstract class ReleaseBundle[
  D <: TypeSet: boundedBy[AnyDataImporterBundle]#is, 
  T <: HList:   towerFor[D]#is
](deps: D) extends Bundle[D,T](deps) with AnyReleaseBundle {

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      // TODO: do what is needed (if anything is needed besides importing)
      success("Bio4j is released")
    }

}

