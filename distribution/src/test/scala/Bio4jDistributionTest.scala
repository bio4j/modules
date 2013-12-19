package ohnosequences.bio4j.tests

import ohnosequences.statika.cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}
import java.io._
import org.scalatest._

import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.bio4j.distributions._ 

class ApplicationTest extends FunSuite with ParallelTestExecution {

  // for running test you need to have this file in your project folder
  val ec2 = EC2.create(new File("/Users/laughedelic/.ec2/Intercrossing.credentials"))

  val dist = Bio4jDistributionDist
  val bundle = Bio4jDistribution.TestTaxonomy

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
