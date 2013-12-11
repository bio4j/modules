package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
// import ohnosequences.statika.bundles._

import ohnosequences.bio4j.bundles.Release.TaxonomyBio4j 

case object TaxonomyBio4jDist extends AWSDistribution(
  metadata = new generated.metadata.StatikaDistributions(),
  ami = ami.AMI149f7863,
  members = TaxonomyBio4j :~: ∅
)
