## elasticsearch-test-client

Simple application to verify TransportClient connectivity to an Elasticsearch cluster.  
Uses Elasticsearch 1.4.3 by default, adjust pom.xml to specify other versions

### Usage

`java -jar esTestTransportClient.jar ClusterName host1:port,host2:port,host3:port`

### Example Output

```
Connecting to eaf1fe49e76ea1b2782ac45879ce5952...
Mar 03, 2015 10:38:53 PM org.elasticsearch.plugins.PluginsService <init>
INFO: [Cybelle] loaded [], sites []
Last status: GREEN
```

This shows that a connection was made and the cluster status was GREEN