/* 
### Bio4j distributions

Here are distribution for deploying Bio4j release bundles
*/

package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.ami._
import ohnosequences.awstools.regions._

// case class ReleaseDistribution(region: AWSRegion = AWSRegion.Ireland) extends AWSDistribution(
case object ReleaseDistribution extends AWSDistribution(
  metadata = new generated.metadata.Bio4jScalaTitandb(),
  ami = amzn_ami_pv_64bit(Region.Ireland)(javaHeap = 6),
  members = Release.NCBITaxonomy :~: Release.GITaxonomyIndex :~: Release.GITaxonomyNodes :~: ∅
)
