package ohnosequences.bio4j.tests

import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.bundles._
import ohnosequences.statika.distributions._

import cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}
import java.io._
import org.scalatest._
import ohnosequences.bio4j.bundles.Release.TaxonomyBio4j 

class ApplicationTest extends FunSuite with ParallelTestExecution {

  // for running test you need to have this file in your project folder
  val ec2 = EC2.create(new File("/Users/laughedelic/.ec2/Intercrossing.credentials"))

  val dist = TaxonomyBio4jDist

  def testBundle[B <: AnyBundle : dist.isMember : dist.isInstallable](bundle: B) = {
    test("Apply "+bundle.name+" bundle to an instance"){
      val userscript = dist.userScript(bundle, RoleCredentials)
      println(userscript)

      val specs = InstanceSpecs(
          instanceType = InstanceType.InstanceType("m1.large")
        , amiId = dist.ami.id
        , keyName = "alexey" 
        // , deviceMapping = Map("/dev/xvdb" -> "ephemeral0")
        , userData = userscript
        , instanceProfileARN = Some("arn:aws:iam::393321850454:instance-profile/god")
        )

      // val result = ec2.applyAndWait(bundle.name, specs, 1) match {
      //   case List(inst) => inst.getTagValue("statika-status") == Some("success")
      //   case _ => false
      // }
      // assert(result)
    }
  }

  testBundle(TaxonomyBio4j)
  
}
