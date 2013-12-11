/* ### Bio4j module bundle

This bundle represents a module of Bio4j DB. A distribution of Bio4j should depend on a number of 
such modules and that will define which data will be imported to the DB.
*/

package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.poly._
import shapeless.ops.hlist._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._
import scala.collection.JavaConversions._

import com.era7.bioinfo.bioinfoutil.Executable


trait AnyImporterBundle extends AnyBundle {
  /* - A program from bio4j-titandb which takes those args */
  val  program: Executable

  /* - Types of arguments for this program */
  type Args <: HList
}

/* Converting Args elements to strings */
object ToString extends Poly1 {
  implicit def caseFile = at[File]{ f => List(f.getAbsolutePath) }
  implicit def caseAny[T] = at[T]{ t => List(t.toString) }
}

abstract class ImporterBundle[A <: HList]
  (val program: Executable)
  extends Bundle() with AnyImporterBundle {

    type Args = A

    // TODO: make wrapper for controlling `execute` call from here
    // def execute[Agrh <: Args](args: Agrh)(implicit m: MapFolder[Agrh, List[String], ToString.type]) = 
    //   program.execute(new java.util.ArrayList(
    //     args.foldMap(List[String]())(ToString){ _ ++ _ }
    //   ))
}

trait AnyDataBundle extends AnyBundle {
  /* - Where to import */
  type InitDB <: Bio4jInstanceBundle
  val  initDB: InitDB

  /* - What to import */
  type RawData <: AnyRawDataBundle
  val  rawData: RawData

  /* - How to import */
  type Importer <: AnyImporterBundle
  val  importer: Importer

  val  args: Importer#Args
}

/* Constructor: */
abstract class DataBundle[
   B <: Bio4jInstanceBundle,
  RD <: AnyRawDataBundle, 
   I <: AnyImporterBundle,
   T <: HList: towerFor[B :~: RD :~: I :~: ∅]#is
](val initDB: B, val rawData: RD, val importer: I)
 (implicit m: MapFolder[I#Args, List[String], ToString.type]) 
  extends Bundle[B :~: RD :~: I :~: ∅, T](initDB :~: rawData :~: importer :~: ∅) with AnyDataBundle {

    type InitDB = B
    type RawData = RD 
    type Importer = I

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      try { 
        importer.program.execute(new java.util.ArrayList(
          args.foldMap(List[String]())(ToString){ _ ++ _ }
        ))
        success(s"Data for the ${name} module is imported to ${initDB.folder}")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }

}
