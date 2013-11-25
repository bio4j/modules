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
// UniprotKB
// are these dependencies real??
case object SwissProt             extends Bundle(GO :~: ENZYME)
case object TrEMBL                extends Bundle(GO :~: ENZYME)
// interactions (??)
case object SwissProtInteractions extends Bundle(SwissProt)
case object TrEMBLInteractions    extends Bundle(TrEMBL)
// isoforms (only for SwissProt?)
case object SwissProtIsoforms     extends Bundle(SwissProt)
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
                                                  TrEMBLInteractions    :~:
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