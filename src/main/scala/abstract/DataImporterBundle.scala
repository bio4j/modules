/* ### Data importer bundle

This bundle uploads data to S3 bucket and provides a link to the object.
*/

package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._
import scala.collection.JavaConversions._

import com.era7.bioinfo.bioinfoutil.Executable

/* Abstract interface: */
trait AnyDataImporterBundle extends AnyBundle {
  type RawData <: AnyRawDataBundle
  val rawData: RawData

  type API  <: AnyAPIBundle
  val api: API

  type Importer <: Executable
  val importer: Importer

  // Arguments for the importer program
  val args: Seq[String]
}

/* Constructor: */
abstract class DataImporterBundle[
  D <: AnyRawDataBundle, 
  A <: AnyAPIBundle, 
  T <: HList: towerFor[D :~: A :~: ∅]#is,
  I <: Executable
](val rawData: D, val api: A)(val importer: I) 
  extends Bundle[D :~: A :~: ∅, T](rawData :~: api :~: ∅) with AnyDataImporterBundle {
    type RawData = D 
    type API = A
    type Importer = I

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      try { 
        importer.execute(new java.util.ArrayList(args))
        success(s"Data for ${name} is imported")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }

}
