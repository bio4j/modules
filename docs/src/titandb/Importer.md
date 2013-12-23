### Index

+ scala
  + [API.scala](API.md)
  + [Importer.scala](Importer.md)
  + [Module.scala](Module.md)
  + [Program.scala](Program.md)
  + [RawData.scala](RawData.md)

------


```scala
package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.bio4j.statika._
import com.ohnosequences.bio4j.titan.programs._
import java.io._
```

According to https://github.com/bio4j/Bio4j/raw/master/ModuleDependencies.png

```scala
object Importer {
```

This bundle is important, it doesn't really import anything, but initializes Bio4j

```scala
  case object InitialBio4j extends Bundle() with AnyImportedDataBundle {
    type RawData = ∅
    val  rawData = ∅
    type ImportedData = ∅
    val  importedData = ∅

    val dbLocation: File = new File("/media/ephemeral0/bio4jtitandb")

    override def install[D <: AnyDistribution](d: D): InstallResults = {
      if (!dbLocation.exists) dbLocation.mkdirs
      InitBio4jTitan.main(Array(dbLocation.getAbsolutePath))
      success(s"Initialized Bio4j DB in ${dbLocation}")
    }
  }
```

### NCBITaxonomy

```scala
  case object NCBITaxonomy extends ImportedDataBundle(
    rawData = RawData.NCBITaxonomy :~: ∅,
    importedData = InitialBio4j :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.NCBITaxonomy(
        nodes  = RawData.NCBITaxonomy.inDataFolder("nodes.dmp"),
        names  = RawData.NCBITaxonomy.inDataFolder("names.dmp"),
        merged = RawData.NCBITaxonomy.inDataFolder("merged.dmp"),
        db     = dbLocation,
        assocUnitprot = true
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

### GITaxonomyIndex

```scala
  case object GITaxonomyIndex extends ImportedDataBundle(
    rawData = RawData.GITaxonomyIndex :~: ∅,
    importedData = NCBITaxonomy :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.IndexNCBITaxonomyByGiId(
        table = RawData.GITaxonomyIndex.inDataFolder("gi_taxid_nucl.dmp"),
        db    = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

### RefSeq

```scala
  case object RefSeq extends ImportedDataBundle(
    rawData = RawData.RefSeq :~: ∅,
    importedData = InitialBio4j :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.RefSeq(
        dataFolder = RawData.RefSeq.dataFolder,
        db         = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

### EnzymeDB

```scala
  case object EnzymeDB extends ImportedDataBundle(
    rawData = RawData.EnzymeDB :~: ∅,
    importedData = InitialBio4j :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.EnzymeDB(
        data = RawData.EnzymeDB.inDataFolder("enzyme.dat"),
        db   = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

### GeneOntology

```scala
  case object GeneOntology extends ImportedDataBundle(
    rawData = RawData.GeneOntology :~: ∅,
    importedData = InitialBio4j :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.GeneOntology(
        data = RawData.GeneOntology.inDataFolder("go.xml"),
        db   = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

### Uniprot

```scala
  case object UniprotSwissProt extends ImportedDataBundle(
    rawData = RawData.UniprotSwissProt :~: ∅,
    importedData = EnzymeDB :~: GeneOntology :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.Uniprot(
        data   = RawData.UniprotSwissProt.inDataFolder("uniprot_sprot.xml"),
        db     = dbLocation,
        config = RawData.UniprotSwissProt.inDataFolder("uniprotData.xml")
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }

  case object UniprotTrEMBL extends ImportedDataBundle(
    rawData = RawData.UniprotTrEMBL :~: ∅,
    importedData = EnzymeDB :~: GeneOntology :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.Uniprot(
        data   = RawData.UniprotTrEMBL.inDataFolder("uniprot_trembl.xml"),
        db     = dbLocation,
        config = RawData.UniprotTrEMBL.inDataFolder("uniprotData.xml")
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

Both things together:

```scala
  case object UniprotKB extends ImportedDataBundle(
    importedData = UniprotSwissProt :~: UniprotTrEMBL :~: ∅
  )
```

### UniRef

```scala
  case object UniRef extends ImportedDataBundle(
    rawData = RawData.UniRef100 :~: RawData.UniRef90 :~: RawData.UniRef50 :~: ∅,
    importedData = UniprotKB :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.UniRef(
        data100 = RawData.UniRef100.inDataFolder("uniref100.xml"),
        data90  = RawData.UniRef90.inDataFolder("uniref90.xml"),
        data50  = RawData.UniRef50.inDataFolder("uniref50.xml"),
        db = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

### ProteinInteractions

```scala
  case object ProteinInteractionsSwissProt extends ImportedDataBundle(
    rawData = RawData.UniprotSwissProt :~: ∅,
    importedData = UniprotSwissProt :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.ProteinInteractions(
        data = RawData.UniprotSwissProt.inDataFolder("uniprot_sprot.xml"),
        db   = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }

  case object ProteinInteractionsTrEMBL extends ImportedDataBundle(
    rawData = RawData.UniprotTrEMBL :~: ∅,
    importedData = UniprotTrEMBL :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.ProteinInteractions(
        data = RawData.UniprotTrEMBL.inDataFolder("uniprot_trembl.xml"),
        db   = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

Both things together:

```scala
  case object ProteinInteractions extends ImportedDataBundle(
    importedData = ProteinInteractionsSwissProt :~: ProteinInteractionsTrEMBL :~: ∅
  )
```

### IsoformSequences

```scala
  case object IsoformSequences extends ImportedDataBundle(
    rawData = RawData.UniprotSprotVarsplic :~: ∅,
    importedData = UniprotKB :~: ∅
  ) {
    override def install[D <: AnyDistribution](d: D): InstallResults = {
      Program.IsoformSequences(
        data = RawData.UniprotSprotVarsplic.inDataFolder("uniprot_sprot_varsplic.fasta"),
        db   = dbLocation
      ).execute ->-
      success(s"Data ${name} is imported to ${dbLocation}")
    }
  }
```

### Everything together

```scala
  case object FullBio4j extends ImportedDataBundle(
    importedData =
      NCBITaxonomy :~: 
      GITaxonomyIndex :~: 
      RefSeq :~: 
      GeneOntology :~: 
      EnzymeDB :~: 
      UniprotKB :~: 
      UniRef :~: 
      ProteinInteractions :~: 
      IsoformSequences :~: 
      ∅
  )

}

```

