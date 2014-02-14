package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import com.ohnosequences.bio4j.titan.programs._
import java.io._
import scala.collection.JavaConversions._
import com.era7.bioinfo.bioinfoutil.Executable

object Program {

  case class UniRef(
    data100 : File, // 1. UniRef 100 xml file
    data90  : File, // 2. UniRef 90 xml file
    data50  : File, // 3. UniRef 50 xml file
    db      : File  // 4. Bio4j DB folder
  ) extends ImporterProgram(new ImportUnirefTitan(), Seq(
    data100.getAbsolutePath, 
    data90.getAbsolutePath, 
    data50.getAbsolutePath, 
    db.getAbsolutePath
  ))

  case class ProteinInteractions(
    data : File, // 1. UniprotKB data xml file 
    db   : File  // 2. Bio4j DB folder
  ) extends ImporterProgram(new ImportProteinInteractionsTitan(), Seq(
    data.getAbsolutePath, 
    db.getAbsolutePath
  ))

  case class IsoformSequences(
    data : File, // 1. Fasta file including all isoforms
    db   : File  // 2. Bio4j DB folder
  ) extends ImporterProgram(new ImportIsoformSequencesTitan(), Seq(
    data.getAbsolutePath, 
    db.getAbsolutePath
  ))

}
