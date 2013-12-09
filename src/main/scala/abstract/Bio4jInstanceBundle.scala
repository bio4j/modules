/* ### Bio4j instance bundle

This bundle represents an instance of initialized Bio4j, 
to which we are going to import data modules.
*/

package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._
import scala.collection.JavaConversions._

import com.era7.bioinfo.bio4j.titan.programs.InitBio4jTitan

abstract class Bio4jInstanceBundle(val folder: File) extends Bundle() {

  override def install[D <: AnyDistribution](d: D): InstallResults = {
    if (!folder.exists) folder.mkdirs
    InitBio4jTitan.main(Array(folder.getAbsolutePath))
    success(s"Initialized Bio4j DB in ${folder}")
  }

}
