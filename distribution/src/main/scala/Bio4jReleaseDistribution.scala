/* 
### Bio4j distributions

Here are distribution for deploying Bio4j release bundles
*/

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.ami._
import Bio4jRelease._

case class Bio4jReleaseDist(region: AWSRegion = AWSRegion.Ireland) extends AWSDistribution(
  metadata = new generated.metadata.Bio4jScalaDistribution(),
  ami = amzn_ami_pv_64bit(region)(javaHeap = 6),
  members = NCBITaxonomy :~: GITaxonomyIndex_1000_1000 :~: GITaxonomyIndex :~: GITaxonomyNodes :~: ∅
)
