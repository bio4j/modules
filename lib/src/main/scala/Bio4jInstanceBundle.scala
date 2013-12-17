/* ### Bio4j instance bundle

This bundle represents an instance of initialized Bio4j, into which we are going to import data modules.
*/

package ohnosequences.bio4j.statika

import ohnosequences.statika._
import java.io._

/* Any Bio4j instance knows where it is */
trait AnyBio4jInstanceBundle extends AnyBundle {
  val dbLocation: File
}
