# demo-server

This applications reads data from a blocking JDBC thread.(currently using H2, but can be used with mysql as well)

and returns a CompletableFuture response with list of data


Flux doesn't have to wait for the blocking thread, however can wait for completablefuture response to start streaming data
