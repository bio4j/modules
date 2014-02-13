/* ### Bio4j instance bundle

This bundle represents an instance of initialized Bio4j, into which we are going to import data modules.
*/

package ohnosequences.bio4j.statika

import ohnosequences.statika._
import java.io._
import com.ohnosequences.bio4j.titan.programs._

/* Any Bio4j instance knows where it is */
trait AnyBio4jInstanceBundle extends AnyBundle {
  val dbLocation: File
}

/* This bundle is important, it doesn't really import anything, but initializes Bio4j */
case object InitialBio4j extends Bundle() with AnyBio4jInstanceBundle {
  val dbLocation: File = new File("/media/ephemeral0/bio4jtitandb")

  override def install[D <: AnyDistribution](d: D): InstallResults = {
    if (!dbLocation.exists) dbLocation.mkdirs
    InitBio4jTitan.main(Array(dbLocation.getAbsolutePath))
    success(s"Initialized Bio4j DB in ${dbLocation}")
  }
}
