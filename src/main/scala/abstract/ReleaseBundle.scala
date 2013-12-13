/* ### Bio4j release

A release is a bundle which combines particular modules and when being applied, actually assembles
the database, which will be later represented by a `Bio4jDistribution`
*/

package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.awstools.s3._
import java.io._

/* Abstract interface: */
trait AnyReleaseBundle extends AnyBundle {
  /* - Where the resulting release will be uploaded */
  val s3address: ObjectAddress

  /* - Module which represents the content of this release */
  type Module <: AnyModuleBundle
  val  module: Module
}

/* Constructor: */
abstract class ReleaseBundle[
  M <: AnyModuleBundle,
  T <: HList: towerFor[M :~: ∅]#is
](val s3address: ObjectAddress, val module: M, val public: Boolean = true) 
  extends Bundle[M :~: ∅, T](module :~: ∅) with AnyReleaseBundle {

    type Module = M

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      try { 
        val s3 = S3.create() // we rely on instance role credentials
        val loader = s3.createLoadingManager()
        loader.uploadDirectory(s3address, module.dbLocation, recursive = true)
        success(s"Release ${name} was uploaded to ${s3address}")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }

}
