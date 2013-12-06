/* ### Raw data bundle

This bundle just downloads raw data files and unpacks them if needed.
*/

package ohnosequences.bio4j.bundles

import ohnosequences.statika._
import java.net.URL
import java.io.File
import sys.process._

/* Abstract interface: */
trait AnyRawDataBundle extends AnyBundle

/* Constructor: */
abstract class RawDataBundle(val url: String) 
  extends Bundle() with AnyRawDataBundle {

    val archive: String = url.split("/").last

    val archType: Option[(String, Seq[String])] = Map(
        ".tar.gz" -> Seq("tar", "xvf", archive),
        ".gz" -> Seq("gunzip", archive)
      ).find{ case (ext, cmd) => archive.endsWith(ext) }

    /* This value provides the link to the data useful for other bundles */
    val dataFolder: File = new File(archive.stripSuffix(archType.map(_._1).getOrElse("")))

    def pathOf(name: String): String = new File(dataFolder, name).getAbsolutePath

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      if (!dataFolder.exists) dataFolder.mkdirs
      
      Seq("curl", url, "-o", archive) @@ dataFolder -&-
      (archType match { 
         case Some((ext, cmd)) => runCommand(cmd @@ dataFolder)()
         case _ => success("Not an archive")
      }) ->-
      success(s"${url} is downloaded and unpacked to ${dataFolder}")
    }

}
