package ohnosequences.bio4j.tests

import ohnosequences.statika.cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}
import java.io._
import org.scalatest._

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.bio4j.distributions._ 
import Bio4jDistribution._

case object TestTaxonomy extends Bundle(GITaxonomyIndex :~: ∅) {
  override def install[D <: AnyDistribution](d: D): InstallResults = {
    {
      val gorilla = GITaxonomyIndex.nodeRetriever.getNCBITaxonByTaxId("9595")
      success("Got the " + gorilla.getScientificName + " taxon!")
    } -&- {
      val neandertal = GITaxonomyIndex.nodeRetriever.getNCBITaxonByTaxId("63221")
      success("Got the " + neandertal.getScientificName + " taxon!")
    }
  }
}

object Metadata extends generated.metadata.Bio4jScalaDistribution {
  override val artifactUrl = 
    new generated.metadata.Bio4jScalaDistribution().artifactUrl.stripSuffix("-fat.jar") + "-test.jar"
}

case object TestDist extends AWSDistribution(
  metadata = Metadata, //new generated.metadata.Bio4jScalaDistribution(),
  ami = Bio4jAMI(6),
  members = TestTaxonomy :~: ∅
)

class ApplicationTest extends FunSuite with ParallelTestExecution {

  // for running test you need to have this file in your project folder
  val ec2 = EC2.create(new File("Intercrossing.credentials"))

  val dist = TestDist
  val bundle = TestTaxonomy
  // val dist = Bio4jReleaseDist
  // val bundle = Bio4jRelease.NCBITaxonomy

  // def testBundle[B <: AnyBundle : dist.isMember : dist.isInstallable](bundle: B) = {
    test("Apply "+bundle.name+" bundle to an instance"){
      val userscript = dist.userScript(bundle, RoleCredentials)
      println(userscript)

      val specs = InstanceSpecs(
          instanceType = InstanceType.InstanceType("m1.large")
        , amiId = dist.ami.id
        , keyName = "statika" 
        , userData = userscript
        , instanceProfile = Some("bio4j-releaser")
        )

      ec2.applyAndWait(bundle.name, specs, 1) match {
        case List(inst) => assert(inst.getTagValue("statika-status") == Some("success"))
        case _ => assert(false)
      }
    }
  // }

  // testBundle(Bio4jReleaseDist, Bio4jRelease.NCBITaxonomy)
  // testBundle(Bio4jReleaseDist, Bio4jRelease.GITaxonomyIndex)
  // testBundle(Bio4jDistribution.TestTaxonomy)
  
}
