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

  case class IndexNCBITaxonomyByGiId(
    table : File, // 1. 1. Tax-id <--> Gi-id table file
    db    : File  // 2. Bio4j DB folder
  ) extends ImporterProgram(new IndexNCBITaxonomyByGiIdTitan(), Seq(
    table.getAbsolutePath, 
    db.getAbsolutePath
  ))

  case class RefSeq(
    dataFolder : File, // 1. Folder name with all the .gbk files
    db         : File  // 2. Bio4j DB folder
  ) extends ImporterProgram(new ImportRefSeqTitan(), Seq(
    dataFolder.getAbsolutePath, 
    db.getAbsolutePath
  ))

  case class EnzymeDB(
    data : File, // 1. Enzyme DB data file (.dat)
    db   : File  // 2. Bio4j DB folder
  ) extends ImporterProgram(new ImportEnzymeDBTitan(), Seq(
    data.getAbsolutePath, 
    db.getAbsolutePath
  ))

  case class GeneOntology(
    data : File, // 1. Gene ontology xml filename
    db   : File  // 2. Bio4j DB folder
  ) extends ImporterProgram(new ImportGeneOntologyTitan(), Seq(
    data.getAbsolutePath, 
    db.getAbsolutePath
  ))

  case class Uniprot(
    data   : File, // 1. Uniprot xml filename 
    db     : File, // 2. Bio4j DB folder
    config : File  // 3. Config XML file
  ) extends ImporterProgram(new ImportUniprotTitan(), Seq(
    data.getAbsolutePath, 
    db.getAbsolutePath,
    config.getAbsolutePath
  ))

  case class Uniref(
    data100 : File, // 1. Uniref 100 xml filename
    data90  : File, // 2. Uniref 90 xml filename
    data50  : File, // 3. Uniref 50 xml filename
    db      : File  // 4. Bio4j DB folder
  ) extends ImporterProgram(new ImportUnirefTitan(), Seq(
    data100.getAbsolutePath, 
    data90.getAbsolutePath, 
    data50.getAbsolutePath, 
    db.getAbsolutePath
  ))

  case class IsoformSequences(
    data : File, // 1. Fasta file including all isoforms
    db   : File  // 2. Bio4j DB folder
  ) extends ImporterProgram(new ImportIsoformSequencesTitan(), Seq(
    data.getAbsolutePath, 
    db.getAbsolutePath
  ))

  case class ProteinInteractions(
    data : File, // 1. Uniprot xml filename
    db   : File  // 2. Bio4j DB folder
  ) extends ImporterProgram(new ImportProteinInteractionsTitan(), Seq(
    data.getAbsolutePath, 
    db.getAbsolutePath
  ))

}
