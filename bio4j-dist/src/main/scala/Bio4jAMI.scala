/* 
### Bio4j AMI

This is an AMI, modified for Bio4j specifics (such as regulated java heap size)
*/

package ohnosequences.bio4j.distributions

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.ami._

/* 
Based on the standard ami-f1ec0e86: "PV Instance Store 64-bit (eu-west-1)" 
Should be storage backed.
*/
case class Bio4jAMI(heap: Int) // in Gb
  extends AmazonLinuxAMI[FatJarMetadata]("ami-f1ec0e86", "2013.09") {

  // TODO: use alternatives
  def preparing(creds: AWSCredentials) = """
    |aws s3 cp s3://resources.ohnosequences.com/java7-oracle.rpm java7-oracle.rpm
    |aws s3 cp s3://resources.ohnosequences.com/scala-2.10.3.rpm scala-2.10.3.rpm
    |yum install -y java7-oracle.rpm scala-2.10.3.rpm
    |alias java=java7
    |""".stripMargin

    def building(
        md: MetadataBound
      , distName: String
      , bundleName: String
      , creds: AWSCredentials = RoleCredentials
      ): String = AMI149f7863.building(md, distName, bundleName, creds)

    // Here is the main difference:
    def applying: String = s"""
      |java -d64 -Xmx${heap}G -cp .:dist.jar apply
      |""".stripMargin

    def tag(state: String): String = AMI149f7863.tag(state)

}
