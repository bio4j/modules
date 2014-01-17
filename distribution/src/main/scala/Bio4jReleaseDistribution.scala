/* 
### Bio4j distributions

Here are distribution for deploying Bio4j release bundles
*/

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import Bio4jRelease._

case object Bio4jReleaseDist extends AWSDistribution(
  metadata = new generated.metadata.Bio4jScalaDistribution(),
  ami = Bio4jAMI(6),
  members = NCBITaxonomy :~: GITaxonomyIndex :~: ∅
)
