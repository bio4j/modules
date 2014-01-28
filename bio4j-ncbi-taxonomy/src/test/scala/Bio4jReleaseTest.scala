package ohnosequences.bio4j.tests

import java.io._
import org.scalatest._

import ohnosequences.statika.cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.ami._
import ohnosequences.bio4j.bundles._ 

class ApplicationTest extends FunSuite with ParallelTestExecution {

  // for running test you need to have this file in your project folder
  val ec2 = EC2.create(new File("Intercrossing.credentials"))

  val dist = NCBITaxonomyReleaseDistribution
  val bundle = NCBITaxonomyRelease

  test("Apply "+bundle.name+" bundle to an instance"){
    val userscript = dist.userScript(bundle, RoleCredentials)
    println(userscript)

    val specs = InstanceSpecs(
        instanceType = InstanceType.m1_large
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
  
}
