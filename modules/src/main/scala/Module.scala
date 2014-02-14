package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import com.ohnosequences.bio4j.titan.programs._
import java.io._

object Module {

  case object UniRef extends ModuleBundle(API.UniRef, Importer.UniRef)

  case object ProteinInteractionsSwissProt extends ModuleBundle(API.ProteinInteractionsSwissProt, Importer.ProteinInteractionsSwissProt)
  case object ProteinInteractionsTrEMBL    extends ModuleBundle(API.ProteinInteractionsTrEMBL, Importer.ProteinInteractionsTrEMBL)
  case object ProteinInteractions extends ModuleBundle(API.ProteinInteractions, Importer.ProteinInteractions)

  case object IsoformSequences extends ModuleBundle(API.IsoformSequences, Importer.IsoformSequences)

  /* This module combines all others */
  case object FullBio4j extends ModuleBundle(API.FullBio4j, Importer.FullBio4j)

}
