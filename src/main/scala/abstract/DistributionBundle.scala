/* ### Bio4j distribution

Distribution represents a release, that was already deployed.
*/

package ohnosequences.bio4j.bundles

import ohnosequences.statika._

// TODO: a bundle with a link to the deployed `ReleaseBundle` in S3 may be with some API

trait AnyDistributionBundle extends AnyBundle {
  val s3Address: String
}
