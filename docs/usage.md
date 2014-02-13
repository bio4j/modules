## Usage of Bio4j modules system

Now Bio4j is separated on modules with Statika and these modules are combinable in a certain way, it's easy to create new ones and prepare releases of the Bio4j DB that suits your needs in the best way.


### Making new modules

As you can see in the [`ModuleBundle` source](../src/lib/ModuleBundle.md), a Module is a statika bundle, which combines

- an API bundle, which does nothing so far, but will represent the proper usage interface for this module;
- an imported data bundle, which represents the real data imported into Bio4j that you can use.

Both these parts have their own dependency hierarchies that should be preserved. So first you should create an `APIBundle` and an `ImportedDataBundle`, then you just declare a module as their combination.

For example:

```scala
object Module {

  case object GITaxonomyIndex extends ModuleBundle(API.GITaxonomyIndex, Importer.GITaxonomyIndex)

}
```

`TODO:` more detailes to be written...


### Making new releases

A release bundle is supposed to "import" a Bio4j build, i.e. to create a module bundle in real and upload it to an S3 bucket. For example:

```scala
object Release {

  case object GITaxonomyIndex extends ReleaseBundle(
    ObjectAddress("my.nonexisting.bucket", "bio4j/indexed_taxonomy"), 
    Module.GITaxonomyIndex
  )

}
```


### Using existing releases

Ok, you have a realese bundle, which was release as an artifact (i.e. was succsessfully applied). Now you can use it to create a "distribution" of Bio4j DB. For example, we released (so far just published a snapshot) `Release.GITaxonomyIndex` bundle as an artifact `"ohnosequences" %% "bio4j-scala-distribution" % "0.1.0-SNAPSHOT"`, so we include it in our sbt project. 

Now in code, we can create a `DistributionBundle`:

```scala
object Bio4jDistribution {

  case object GITaxonomyIndex extends DistributionBundle(
    Release.GITaxonomyIndex,
    new File("/media/ephemeral0/bio4j/indexed_taxonomy/")
  )

}
```

Now we know, that if we depend on this bundle, it will download this release of Bio4j and initialize it, providing us following:

```scala
trait AnyDistributionBundle extends AnyBio4jInstanceBundle {
  type API <: AnyAPIBundle
  val  api: API

  def bio4jManager: Bio4jManager  
  def nodeRetriever: NodeRetrieverTitan    
}
```

Now we can try to use this `nodeRetriever` as we would do it normally with Bio4j from java:

```scala
case object TestTaxonomy extends Bundle(Bio4jDistribution.GITaxonomyIndex :~: ∅) {
  override def install[D <: AnyDistribution](d: D): InstallResults = {
    try { 

      val human = GITaxonomyIndex.nodeRetriever.getNCBITaxonByTaxId("9606")
      println("human.getName: " + human.getName)
      println("human.getScientificName: " + human.getScientificName)
      println("human.getComments: " + human.getComments)
      success("Got the Human taxon!")

    } catch {
      case e: Exception => failure(e.toString)
    }
  }
}
```

> Note: this code was not properly tested, but you got the idea, right?

Now having this bundle, you need a distribution, which will apply it:

```scala
case object Bio4jDistributionDist extends AWSDistribution(
  metadata = new generated.metadata.Bio4jScalaDistribution(),
  ami = Bio4jAMI(6),
  members = TestTaxonomy :~: ∅
)
```

> Note usage of `Bio4jAMI` here — it's an AMI with some tuning for Bio4j. Not sure, if it's needed for this bundle, it was needed for release bundle, as they need custom memory settings.

And finally,

```scala
val ec2 = EC2.create(new File("..."))
val dist = Bio4jDistributionDist
val bundle = Bio4jDistribution.TestTaxonomy
val userscript = dist.userScript(bundle, RoleCredentials)

val specs = InstanceSpecs(
  instanceType = InstanceType.InstanceType("m1.large"),
  amiId = dist.ami.id,
  keyName = "statika" ,
  userData = userscript,
  instanceProfile = Some("bio4j-releaser")
)

ec2.applyAndWait(bundle.name, specs, 1) match {
  case List(inst) => assert(inst.getTagValue("statika-status") == Some("success"))
  case _ => assert(false)
}
```

Or something like this. The key point here is just `dist.userscript`.
