import org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.client.transport.TransportClient;


public class esTestTransportClient {
	private String cluster_name;
	private String[] host_list;
	
	public esTestTransportClient(String cname, String hlist) {
		cluster_name = cname;
		host_list = hlist.split(",", -1);
	}
	
	private void connect() {
		System.out.println("Connecting to " + cluster_name + "...");
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", cluster_name).build();
		
		TransportClient client = new TransportClient(settings);
		for(String host: host_list) {
			int port = 9300;
			
			//Parse port from host string
			String[] parsedHost = host.split(":", 2);
			if(parsedHost.length == 2) {
				host = parsedHost[0];
				port = Integer.parseInt(parsedHost[1]);
			}
			
			client.addTransportAddress(new InetSocketTransportAddress(host, port));
		}
		
		final ClusterHealthStatus status = client.admin().cluster().prepareHealth().get().getStatus();
		System.out.println("Last status: " + status.name());
		client.close();
	}
	
	public static void main(String[] args) {
		String cname;
		String hlist;
		
		if(args.length != 0) {
			cname = args[0];
			hlist = args[1];
			
			try {
				esTestTransportClient esclient = new esTestTransportClient(cname, hlist);
				esclient.connect();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			System.exit(0);
		}
	}
}