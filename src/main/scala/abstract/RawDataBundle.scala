/* ### Raw data bundle

This bundle just downloads raw data files and unpacks them if needed.
*/

package ohnosequences.bio4j.bundles

import ohnosequences.statika._
import java.io._

/* Abstract interface: */
trait AnyRawDataBundle extends AnyBundle

/* Constructor: */
abstract class RawDataBundle(val url: String) 
  extends Bundle() with AnyRawDataBundle {

    val archive: String = url.split("/").last

    val unpack: (String, InstallResults) = 
      Map[String, InstallResults](
        ".tar.gz" -> Seq("tar", "-xvf", archive),
        ".gz" -> Seq("gunzip", archive)
      ).find{ case (ext, cmd) => archive.endsWith(ext) }.
        getOrElse("" -> success("Not an archive"))

    /* This value provides the link to the data useful for other bundles */
    val dataFile: File = new File(archive.stripSuffix(unpack._1))

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Seq("curl", url, "-o", archive) -&-
      unpack._2 ->-
      success(s"${url} is downloaded and unpacked to ${dataFile}")
    }

}
