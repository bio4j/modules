# AWS resources as bundles

On a first stage we will work assuming that we can access binary platform-specific data as S3 objects and / or EBS snapshots. This obviously implies that a particular technology stack (titandb, neo4j, ...) is fixed throughout; let's take titandb as an example.

We have `bio4j-titandb` which contains an implementation of `bio4j-model`, possibly adapted to the particularities and features of titandb. We have a binary distribution of the database: the set of files that comprise a titandb database. This database needs a particular version of the `bio4j-titandb` codebase; we can say that it _depends_ on that library.

In principle, one resource per technology could be enough, to keep things simple; we can further extend this at the level of dependencies later. Also, it could be a good idea to declare _what_ a binary distribution of that database is (as an abstract `Bundle` trait).

``` scala
case object bio4jTitanDB extends Bundle(bio4jModel)
// maybe with TitanDBInstance?
case object bio4jTitanDBData extends Bundle(bio4jTitanDB)
case object bio4jTitanDBInstance extends Bundle(bio4jTitanDBData)
```

The `install` method of `bio4jTitanDBInstance` will of course (through its dependencies) retrieve binaries from S3 and/or create EBS volumes, and set any conf needed (possibly including a TitanDB driver conf). We will use this bundle as a foundation for all further infrastructure needing a TitanDB-based bio4j instance. This will be as easy as

``` scala
case object doSomethingWithBio4j extends Bundle(bio4jTitanDBInstance) {

  // do something
}
```

In principle, this is all that's needed for a minimal reproducible deployment.



