# aws resources

We have two completely different scenarios:

- data loading
- bio4j deployment

## data loading

### instance type

It is not so clear whether the most important limiting factor here is memory or disk. In principle, SSD-based instances could be the right choice.

### raw data

We will separate downloading raw data from the original sources and putting it in S3 (which can be done by pretty stupid machines). Once the data is there in S3 (compressed or not) an I/O-optimized machine will download it from S3 and run the import there.

### imported db binaries

It is not so easy to determine where to put them; we need to measure how long it would take to download them from S3; possibly split them into several objects is a good idea; or just push them in parallel: see [pushing the limits of S3 upload performance](http://improve.dk/pushing-the-limits-of-amazon-s3-upload-performance/). According to that, we could get close to `1GB/s`.

If possible, I want to avoid EBS.

## deployment (querying)

### instance type

We need to know if it would be possible to load everything in memory, and if so how costly it is. I would try first with SSD-based instances, probably CPU-optimized if available.

### data

We will use instance store here, so we need to get the db binaries from somewhere. The two options I see are

1. get them from S3 (multipart, several objects, ...)
2. instantiate volume from snapshot, copy to instance store, delete volume. Ugly and dangerous

#### integrity checks

This is a bit tricky with multipart upload/download in general. However, as in our case we are downloading the files either way we should

1. when uploading to S3 the binary data, upload its md5 (as object metadata)
2. when downloading from S3, compute it locally after download and check