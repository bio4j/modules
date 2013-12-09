/* ### Bio4j release

A release is a bundle which combines particular importers and when being applied, actually assembles
the database, which will be later represented by a `Bio4jDistribution`
*/

package ohnosequences.bio4j.bundles

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import java.io._

import com.era7.bioinfo.bio4j.titan.programs.InitBio4jTitan

/* Abstract interface: */
trait AnyReleaseBundle extends AnyBundle

/* Constructor: */
abstract class ReleaseBundle[
  D <: TypeSet: boundedBy[AnyModuleBundle]#is, 
  T <: HList:   towerFor[D]#is
](deps: D) extends Bundle[D,T](deps) with AnyReleaseBundle {

    override def install[D <: AnyDistribution](d: D): InstallResults =
      success("All modules are imported")

}

/* This distribution constructor should be used to deploy a Bio4j instance. 
   The hypotetic code would look like:

   ```scala
   import ohnosequences.statika.aws._
   // ...

   case object MyBio4j extends ReleaseBundle(Module.Foo :~: Module.Bar :~: ∅)
   
   case object MyBio4jDist extends ReleaseDistribution(MyBio4j)
     with AWSDistribution(
            metadata = generated.metadata.MyBio4jProject,
            ami = ami.AMI149f7863,
            members = ∅
          )
   ```
   
   Then you can just apply `MyBio4j` bundle with `MyBio4jDist` distribution in 
   the standard way. The key thing here is in initializing DB before installing 
   any module bundles.
*/
abstract class ReleaseDistribution[
  RB <: AnyReleaseBundle
](val release: RB, val folder: File = new File("bio4jdb"))
  extends Distribution(members = release :~: ∅) {

    /* This is important step on which we initialize a Bio4j DB instance, 
       that will be performed before installing any module bundles.
    */
    def setContext: InstallResults = {
      if (!folder.exists) folder.mkdirs
      InitBio4jTitan.main(Array(folder.getAbsolutePath))
      success(s"Initialized Bio4j DB in ${folder}")
    }

}
