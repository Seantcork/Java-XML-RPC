import java.util.List; 
import java.util.ArrayList; 
import java.net.URL;     
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * A simple example XML-RPC client program.
 */
public class Client {

  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Usage: [server] [x] [y]");
      return;
    }
    String hostname = args[0];
    int x = Integer.parseInt(args[1]);
    int y = Integer.parseInt(args[2]);

    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    XmlRpcClient client = null;
    
    try {
      config.setServerURL(new URL("http://" + hostname + ":8124"));
      client = new XmlRpcClient();
      client.setConfig(config);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
    }

    List<Integer> params = new ArrayList<Integer>();
    params.add(x);
    params.add(y);

    try {
      Object[] result = (Object[]) client.execute("Sample.sumAndDifference", params);
      System.out.println("Sum is " + result[0]);
      System.out.println("Difference is " + result[1]);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
    }
  }

}
