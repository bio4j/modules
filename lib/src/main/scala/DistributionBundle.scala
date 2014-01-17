/* ### Bio4j distribution

This bundle represents the database instance, which was already built by applying a `ReleaseBundle`.
So it is stored somewhere in S3 and this bundle downloads it, initializes TitanDB and provides the 
corresponding abstract API.
*/

package ohnosequences.bio4j.statika

import shapeless._
import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.awstools.s3._
import com.ohnosequences.bio4j.titan.model.util._
import java.io.File

/* Abstract interface: */
trait AnyDistributionBundle extends AnyBio4jInstanceBundle {
  type API <: AnyAPIBundle
  val  api: API

  lazy val bio4jManager: Bio4jManager = new Bio4jManager(dbLocation.getAbsolutePath)
  lazy val nodeRetriever: NodeRetrieverTitan = new NodeRetrieverTitan(bio4jManager)
}

/* Constructor: */
abstract class DistributionBundle[R <: AnyReleaseBundle](val release: R, destPrefix: File)
  extends Bundle() with AnyDistributionBundle {

    type API = release.module.API
    val  api = release.module.api

    val dbLocation = new File(destPrefix, release.s3address.key) 

    // def bio4jManager
    // def nodeRetriever

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      try { 
        val s3 = S3.create() // we rely on instance role credentials
        val loader = s3.createLoadingManager()

        if (!destPrefix.exists) destPrefix.mkdirs

        loader.downloadDirectory(release.s3address, destPrefix)

        success(s"Distribution ${name} was dowloaded to ${dbLocation} and initialized")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }

}
