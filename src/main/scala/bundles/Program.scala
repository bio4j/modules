package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.statika._
import com.era7.bioinfo.bio4j.titan.programs._
import java.io._

object Program {

  case class NCBITaxonomy(
    nodes         : File,    // 1. Nodes DMP filename
    names         : File,    // 2. Names DMP filename
    merged        : File,    // 3. Merged DMP filename
    db            : File,    // 4. Bio4j DB folder
    assocUnitprot : Boolean  // 5. Associate Uniprot taxonomy (true/false)
  ) extends ImporterProgram(new ImportNCBITaxonomyTitan(), Seq(
    nodes.getAbsolutePath, 
    names.getAbsolutePath, 
    merged.getAbsolutePath,
    db.getAbsolutePath,
    assocUnitprot.toString
  ))

}
