/* 
### Bio4j distributions

Here are distribution for deploying Bio4j release bundles
*/

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._

import ohnosequences.bio4j.bundles.Release.TaxonomyBio4j 

case object TaxonomyBio4jDist extends AWSDistribution(
  metadata = new generated.metadata.StatikaDistributions(),
  ami = Bio4jAMI(6),
  members = TaxonomyBio4j :~: ∅
)
