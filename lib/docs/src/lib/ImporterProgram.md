### Index

+ scala
  + [APIBundle.scala](APIBundle.md)
  + [Bio4jInstanceBundle.scala](Bio4jInstanceBundle.md)
  + [DistributionBundle.scala](DistributionBundle.md)
  + [ImportedDataBundle.scala](ImportedDataBundle.md)
  + [ImporterProgram.scala](ImporterProgram.md)
  + [ModuleBundle.scala](ModuleBundle.md)
  + [RawDataBundle.scala](RawDataBundle.md)
  + [ReleaseBundle.scala](ReleaseBundle.md)

------


```scala
package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import com.ohnosequences.bio4j.titan.programs._
import java.io._
import scala.collection.JavaConversions._
import com.era7.bioinfo.bioinfoutil.Executable
```

A abstract cover for importing program from bio4j-titandb

```scala
trait AnyImporterProgram {
  def execute: InstallResults
}

abstract class ImporterProgram(val program: Executable, val args: Seq[String]) 
  extends AnyImporterProgram {
    def execute: InstallResults = {
      try { 
        program.execute(new java.util.ArrayList(args))
        success(s"Data is imported")
      } catch {
        case e: Exception => failure(e.toString)
      }
    }
}

```

