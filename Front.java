import java.util.List; 
import java.util.ArrayList; 
import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;  
import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;

/**
 * A simple example XML-RPC server program.
 */
public class Front { 
  Object[] result;

  public Integer[] sumAndDifference(int x, int y) {
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    XmlRpcClient client = null;
    
    try {
      config.setServerURL(new URL("http://localhost:8880"));
      client = new XmlRpcClient();
      client.setConfig(config);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
    }


    List<Integer> params = new ArrayList<Integer>();
    params.add(x);
    params.add(y);

    try {
      result = (Object[]) client.execute("Order.sumAndDifference", params);
      System.out.println("Sum is " + result[0]);
      System.out.println("Difference is " + result[1]);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
    }

    return result;
    
  }


  public static void main(String[] args) {
    try {
      PropertyHandlerMapping phm = new PropertyHandlerMapping();
      XmlRpcServer xmlRpcServer;
      WebServer server = new WebServer(8888);
      xmlRpcServer = server.getXmlRpcServer();
      phm.addHandler("Sample", Front.class);
      xmlRpcServer.setHandlerMapping(phm);
      server.start();
      System.out.println("front server started");
    } catch (Exception e) {
      System.err.println("Server exception: " + e);
    }
  }

}
