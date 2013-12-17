package ohnosequences.bio4j.bundles

import ohnosequences.bio4j.statika._
import ohnosequences.typesets._
import ohnosequences.statika._

object API {

  // Taxonomy
  case object NCBITaxonomy    extends APIBundle

  case object GITaxonomyIndex extends APIBundle(NCBITaxonomy :~: ∅)
  // RefSeq
  case object RefSeq          extends APIBundle()
  // Gene Ontology
  case object GeneOntology    extends APIBundle()
  // Expasy EnzymeDB
  case object EnzymeDB        extends APIBundle()
  // Keywords
  case object Keywords        extends APIBundle()
  // Pfam
  case object Pfam            extends APIBundle()
  // Interpro
  case object Interpro        extends APIBundle()
  // Reactome
  case object Reactome        extends APIBundle()
  // Article citations
  case object Articles        extends APIBundle()
  // Online article citations
  case object OnlineArticles  extends APIBundle()
  // Thesis citations
  case object Thesis          extends APIBundle()
  // Book citations
  case object Books           extends APIBundle()
  // Submission citations
  case object Submissions     extends APIBundle()
  // Patent citations
  case object Patents         extends APIBundle()
  // Unpublished observation citations
  case object UnpublishedObservations extends APIBundle()
  // Citation references (Articles, books, submissions...)
  case object Citations extends APIBundle(
    Articles :~: 
    OnlineArticles :~: 
    Thesis :~: 
    Books :~: 
    Submissions :~: 
    Patents :~:       
    UnpublishedObservations :~:
    ∅
  )
  // UniprotKB
  // are these dependencies real??
  case object UniprotSwissProt extends APIBundle(       
    GeneOntology :~: 
    EnzymeDB :~: 
    RefSeq :~: 
    Keywords :~: 
    Pfam :~: 
    Interpro :~: 
    Reactome :~: 
    Citations :~:    
    GITaxonomyIndex :~:
    ∅
  )

  case object UniprotTrEMBL extends APIBundle(       
    GeneOntology :~:    
    EnzymeDB :~:      
    RefSeq :~:    
    Keywords :~:    
    Pfam :~:      
    Interpro :~:    
    Reactome :~:      
    Citations :~:    
    GITaxonomyIndex :~:
    ∅
  )
  // interactions
  case object ProteinInteractionsSwissProt extends APIBundle(UniprotSwissProt :~: ∅)
  case object ProteinInteractionsTrEMBL    extends APIBundle(UniprotTrEMBL :~: ∅)
  case object ProteinInteractions   extends APIBundle(
    ProteinInteractionsSwissProt :~: 
    ProteinInteractionsTrEMBL :~: 
    ∅
  )
  // isoforms 
  case object IsoformSequencesSwissProt extends APIBundle(UniprotSwissProt :~: ∅)
  case object IsoformSequencesTrEMBL    extends APIBundle(UniprotTrEMBL :~: ∅)
  case object IsoformSequences  extends APIBundle(
    IsoformSequencesSwissProt :~: 
    IsoformSequencesTrEMBL :~: 
    ∅
  )
  // UniRef stuff
  case object UniRef100  extends APIBundle(UniprotSwissProt :~: UniprotTrEMBL :~: ∅)
  case object UniRef90   extends APIBundle(UniprotSwissProt :~: UniprotTrEMBL :~: ∅)
  case object UniRef50   extends APIBundle(UniprotSwissProt :~: UniprotTrEMBL :~: ∅)
  case object UniRef     extends APIBundle(
    UniRef50 :~:
    UniRef90 :~:
    UniRef100 :~:
    ∅
  )
  // the full thing?
  case object UniprotKB extends APIBundle(
    UniprotSwissProt :~: 
    IsoformSequencesSwissProt :~:
    ProteinInteractionsSwissProt :~:
    UniprotTrEMBL :~:
    ProteinInteractionsTrEMBL :~:     
    IsoformSequencesTrEMBL :~:
    ∅
  )
  // all data?
  case object FullBio4j extends APIBundle(
    RefSeq :~:
    GeneOntology :~:
    NCBITaxonomy :~:
    EnzymeDB :~:
    UniprotKB :~:
    UniRef :~:
    ∅
  )


}
