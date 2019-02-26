import java.util.ArrayList; 
import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;  
import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import java.util.*;

/**
 * A simple example XML-RPC server program.
 */
public class Front { 
  String result;
  String welcome_reply;

  public String HandleRequest(String function, String arg) {
    System.out.println("got here");
    System.out.println(function);
    System.out.println(arg);
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    XmlRpcClient client = null;
    
    try {
      config.setServerURL(new URL("http://localhost:8123"));
      client = new XmlRpcClient();
      client.setConfig(config);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
    }

    ArrayList<String> params = new ArrayList<String>();
    params.add(arg);

    if(function.equals("search")){
      try {
        result = (String)client.execute("Catalog.query_by_topic", params);
      } catch (Exception e) {
        System.err.println("Client exception: " + e);
      }

    }

    else if(function.equals("lookup")){
      try {
        result = (String) client.execute("Catalog.query_by_item", params);
      } catch (Exception e) {
        System.err.println("Client exception: " + e);
      }
    }

    else if(function.equals("buy")){
      System.out.println("hey");
    }

    return result;
    
  }

  public String welcome(int x, int y) {
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    XmlRpcClient client = null;
    
    List<Integer> params = new ArrayList<Integer>();
    params.add(x);
    params.add(y);
    System.out.println("in welcome in fron server");
    try {
      config.setServerURL(new URL("http://localhost:8123"));
      client = new XmlRpcClient();
      client.setConfig(config);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
    }

    try {
      welcome_reply = (String) client.execute("Catalog.welcome", params);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
    }

    return welcome_reply;
  }


  public static void main(String[] args) {
    try {
      PropertyHandlerMapping phm = new PropertyHandlerMapping();
      XmlRpcServer xmlRpcServer;
      WebServer server = new WebServer(8124);
      xmlRpcServer = server.getXmlRpcServer();
      phm.addHandler("Front", Front.class);
      xmlRpcServer.setHandlerMapping(phm);
      server.start();
      System.out.println("XML-RPC server started");
    } catch (Exception e) {
      System.err.println("Server exception: " + e);
    }
  }

}
