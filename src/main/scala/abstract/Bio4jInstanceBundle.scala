/* ### Bio4j instance bundle

This bundle represents an instance of initialized Bio4j, into which we are going to import data modules.
*/

package ohnosequences.bio4j.bundles

import ohnosequences.statika._
import java.io._

/* Any Bio4j instance knows where it is */
trait AnyBio4jInstanceBundle extends AnyBundle {
  val dbLocation: File
}

/* And it also knows how to initialize itself */
abstract class Bio4jInstanceBundle(val dbLocation: File) extends Bundle() with AnyBio4jInstanceBundle {

  import com.era7.bioinfo.bio4j.titan.programs.InitBio4jTitan

  override def install[D <: AnyDistribution](d: D): InstallResults = {
    if (!dbLocation.exists) dbLocation.mkdirs
    InitBio4jTitan.main(Array(dbLocation.getAbsolutePath))
    success(s"Initialized Bio4j DB in ${dbLocation}")
  }

}
