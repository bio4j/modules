/* 
### Bio4j distributions

Here are distribution for deploying Bio4j release bundles
*/

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import Bio4jRelease._

case object Bio4jDist extends AWSDistribution(
  metadata = new generated.metadata.Bio4jScalaDist(),
  ami = Bio4jAMI(6),
  members = TaxonomyBio4j :~: IndexedTaxonomyBio4j :~: FullBio4j :~: ∅
)
