package ohnosequences.bio4j.bundles

import ohnosequences.typesets._
import ohnosequences.statika._

object API {

  // Taxonomy
  case object NCBITaxonomy    extends APIBundle
/*
  case object GITaxonomyIndex extends APIBundle(NCBITaxonomy :~: ∅)
  // RefSeq
  case object RefSeq          extends APIBundle()
  // Gene Ontology
  case object GO              extends APIBundle()
  // Expasy EnzymeDB
  case object ENZYME          extends APIBundle()
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
    Articles                :~: 
    OnlineArticles          :~: 
    Thesis                  :~: 
    Books                   :~: 
    Submissions             :~: 
    Patents                 :~:       
    UnpublishedObservations :~:
    ∅
  )
  // UniprotKB
  // are these dependencies real??
  case object SwissProt extends APIBundle(       
    GO               :~: 
    ENZYME           :~: 
    RefSeq           :~: 
    Keywords         :~: 
    Pfam             :~: 
    Interpro         :~: 
    Reactome         :~: 
    Citations        :~:    
    GITaxonomyIndex  :~:
    ∅
  )

  case object TrEMBL extends APIBundle(       
    GO               :~:    
    ENZYME           :~:      
    RefSeq           :~:    
    Keywords         :~:    
    Pfam             :~:      
    Interpro         :~:    
    Reactome         :~:      
    Citations        :~:    
    GITaxonomyIndex  :~:
    ∅
  )
  // interactions
  case object SwissProtInteractions extends APIBundle(SwissProt :~: ∅)
  case object TrEMBLInteractions    extends APIBundle(TrEMBL    :~: ∅)
  // isoforms 
  case object SwissProtIsoforms extends APIBundle(SwissProt :~: ∅)
  case object TrEMBLIsoforms    extends APIBundle(TrEMBL    :~: ∅)
  // UniRef stuff
  case object UniRef100  extends APIBundle(SwissProt :~: TrEMBL :~: ∅)
  case object UniRef90   extends APIBundle(SwissProt :~: TrEMBL :~: ∅)
  case object UniRef50   extends APIBundle(SwissProt :~: TrEMBL :~: ∅)
  case object FullUniRef extends APIBundle(
    UniRef50  :~:
    UniRef90  :~:
    UniRef100 :~:
    ∅
  )
  // the full thing?
  case object UniprotKB extends APIBundle(
    SwissProt             :~: 
    SwissProtIsoforms     :~:
    SwissProtInteractions :~:
    TrEMBL                :~:
    TrEMBLInteractions    :~:     
    TrEMBLIsoforms        :~:
    ∅
  )
  // all data?
  case object FullBio4j extends APIBundle(
    RefSeq        :~:
    GO            :~:
    NCBITaxonomy  :~:
    ENZYME        :~:
    UniprotKB     :~:
    FullUniRef    :~:
    ∅
  )
*/

}
