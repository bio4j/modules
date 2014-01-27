/* 
### Bio4j distributions

Here are distribution for deploying Bio4j release bundles
*/

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.ami._
import Bio4jDistribution._

case object Bio4jDistributionDist extends AWSDistribution(
  metadata = new generated.metadata.Bio4jScalaDistribution(),
  ami = amzn_ami_pv_64bit(AWSRegion.Ireland)(javaHeap = 6),
  members = NCBITaxonomy :~: GITaxonomyIndex :~: ∅
)
