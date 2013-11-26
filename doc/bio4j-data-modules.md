# bio4j modules

> I'm getting all this info from [bio4j wiki - bio4j modules](https://github.com/bio4j/Bio4j/wiki/Bio4j-modules)

Right now you can get different data modules and declare the import using `xml` files. I'm missing some details here, but it looks like the basic structure is:

``` scala
package bio4j.data
// taxonomy
case object NCBITaxonomy          extends Bundle
case object GITaxonomyIndex       extends Bundle(NCBITaxonomy)
// RefSeq
case object RefSeq                extends Bundle()
// Gene Ontology
case object GO                    extends Bundle()
// Expasy EnzymeDB
case object ENZYME                extends Bundle()
// Keywords
case object Keywords              extends Bundle()
// Pfam
case object Pfam                  extends Bundle()
// Interpro
case object Interpro              extends Bundle()
// Reactome
case object Reactome              extends Bundle()
// Article citations
case object Articles              extends Bundle()
// Online article citations
case object OnlineArticles        extends Bundle()
// Thesis citations
case object Thesis                extends Bundle()
// Book citations
case object Books                 extends Bundle()
// Submission citations
case object Submissions           extends Bundle()
// Patent citations
case object Patents               extends Bundle()
// Unpublished observation citations
case object UnpublishedObservations extends Bundle()
// Citation references (Articles, books, submissions...)
case object Citations             extends Bundle(				Articles                :~: 
	OnlineArticles          :~: 
	Thesis                  :~: 
	Books                   :~: 
	Submissions             :~: 
	Patents                 :~: 			UnpublishedObservations	                                                 )
// UniprotKB
// are these dependencies real??
case object SwissProt             extends Bundle(				GO               :~: 		ENZYME           :~: 			RefSeq           :~: 		Keywords         :~: 		Pfam             :~: 			Interpro         :~: 		Reactome         :~: 			Citations        :~:		GITaxonomyIndex	                                                 )

case object TrEMBL                extends Bundle(				GO               :~: 		ENZYME           :~: 			RefSeq           :~: 		Keywords         :~: 		Pfam             :~: 			Interpro         :~: 		Reactome         :~: 			Citations        :~:		GITaxonomyIndex	                                                )
// interactions
case object SwissProtInteractions extends Bundle(SwissProt)
case object TrEMBLInteractions    extends Bundle(TrEMBL)
// isoforms 
case object SwissProtIsoforms     extends Bundle(SwissProt)
case object TrEMBLIsoforms        extends Bundle(TrEMBL)
// UniRef stuff
case object UniRef100             extends Bundle(SwissProt :~: TrEMBL)
case object UniRef90              extends Bundle(SwissProt :~: TrEMBL)
case object UniRef50              extends Bundle(SwissProt :~: TrEMBL)
case object FullUniRef            extends Bundle(
                                                  UniRef50  :~:
                                                  UniRef90  :~:
                                                  UniRef100 :~:
                                                )
// the full thing?
case object UniprotKB             extends Bundle(
                                                  SwissProt             :~: 
                                                  SwissProtIsoforms     :~:
                                                  SwissProtInteractions :~:
                                                  TrEMBL                :~:
                                                  TrEMBLInteractions    :~:			TrEMBLIsoforms
                                                )
// all data?
case object FullBio4j             extends Bundle(
                                                  RefSeq        :~:
                                                  GO            :~:
                                                  NCBITaxonomy  :~:
                                                  ENZYME        :~:
                                                  UniprotKB     :~:
                                                  FullUniRef    :~:
                                                )
```

This needs further clarification, hopefully as part of ohnosequences/bio4j-scala#7
