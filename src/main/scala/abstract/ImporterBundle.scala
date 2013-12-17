/* ### Bio4j module bundle

This bundle represents imported data of Bio4j DB. It's key feature is that it can depend on ohter 
imported data bundles (and optionally on some raw data bundles), so that you can preserve the order
of importing data and can easily create bundles which don't do anything by themselves, but combine 
others.
*/

package ohnosequences.bio4j.statika

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._

import java.io._
import scala.collection.JavaConversions._
import com.era7.bioinfo.bioinfoutil.Executable


/* A cover for an importing program from bio4j-titandb */
trait AnyImporterProgram {
  def execute: InstallResults
}

abstract class ImporterProgram(val program: Executable, val args: Seq[String]) 
  extends AnyImporterProgram {
    def execute: InstallResults = {
      try { 
        program.execute(new java.util.ArrayList(args))
        success(s"Data is imported")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }
}

/* Abstract interface: */
trait AnyImportedDataBundle extends AnyBio4jInstanceBundle {

  /* - What to import */
  type RawData <: TypeSet
  val  rawData: RawData

  /* - Dependencies on other importers */
  type ImportedData <: TypeSet
  val  importedData: ImportedData

}

/* Constructor: */
abstract class ImportedDataBundle[
  Rs <: TypeSet: boundedBy[AnyRawDataBundle]#is,
  In <: AnyImportedDataBundle,                        // head
  Is <: TypeSet: boundedBy[AnyImportedDataBundle]#is, // tail
  Ds <: TypeSet,
  Tw <: HList
](val rawData: Rs = ∅, val importedData: In :~: Is)
 (implicit 
    union: UnionSets[Rs, In :~: Is]{ type Out = Ds },
    bound: ofBundles[Ds],
    tower: towerFor[Ds]#is[Tw]
 ) extends Bundle[Ds, Tw](union(rawData, importedData)) with AnyImportedDataBundle {

    type RawData = Rs
    type ImportedData = In :~: Is

    // importedData set should non-empty for this: 
    val dbLocation: File = importedData.head.dbLocation

}
