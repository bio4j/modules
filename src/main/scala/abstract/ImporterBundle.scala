/* ### Bio4j module bundle

This bundle represents a module of Bio4j DB. A distribution of Bio4j should depend on a number of 
such modules and that will define which data will be imported to the DB.
*/

package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._

import java.io._
import scala.collection.JavaConversions._
import com.era7.bioinfo.bioinfoutil.Executable


/* A cover for an importing program from bio4j-titandb */
class ImporterProgram(val program: Executable, val args: Seq[String]) {
  def execute(): Unit = program.execute(new java.util.ArrayList(args))
}

/* Abstract interface: */
trait AnyImporterBundle extends AnyBundle {
  /* - Where to import */
  type InitDB <: AnyBio4jInstanceBundle
  val  initDB: InitDB

  /* - What to import */
  type RawData <: AnyRawDataBundle
  val  rawData: RawData

  /* - How to import */
  val  program: ImporterProgram
}

/* Constructor: */
abstract class ImporterBundle[
  DB <: AnyBio4jInstanceBundle, 
  RD <: AnyRawDataBundle, 
   T <: HList: towerFor[DB :~: RD :~: ∅]#is
](val initDB: DB, val rawData: RD)
  extends Bundle[DB :~: RD :~: ∅, T](initDB :~: rawData :~: ∅) with AnyImporterBundle {

    type InitDB = DB
    type RawData = RD

    // should still define `program`

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      try { 
        program.execute()
        success(s"Data ${rawData.name} is imported to ${initDB.dbLocation}")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }
}
