import java.util.ArrayList; 
import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;  
import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import java.util.*;

public class Order{

  public String buy(String item_num) {
    System.out.println("in order.buy");
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    XmlRpcClient client = null;
    Object[] bookinfo;
    
    try {
      config.setServerURL(new URL("http://localhost:8123"));
      client = new XmlRpcClient();
      client.setConfig(config);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
      return "Error in Order.buy";
    }

    ArrayList<String> params = new ArrayList<String>();
    params.add(item_num);

    try {
      bookinfo = (Object[]) client.execute("Catalog.query_by_item", params);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
      return "Error in Buy";
    }

    if(bookinfo.length == 0) {
      System.out.println("Error finding Book");
      return "Error in Buy";
    }

    if(bookinfo[4].equals("0")) {
      return ("Our apologies" + bookinfo[0] + " by " + bookinfo[2] + " is Out of Stock");
    } 
    else {
      try {
        Object cat = client.execute("Catalog.update", params);
      } catch (Exception e) {
        System.err.println("Client exception: " + e);
        return "Error in Buy";
      }
      return "Purchase Approved";
    }
  }


public static void main(String[] args) {
    try {
      int x = 1;
      System.out.println(x);
      PropertyHandlerMapping phm = new PropertyHandlerMapping();
      XmlRpcServer xmlRpcServer;
      WebServer server = new WebServer(8125);
      xmlRpcServer = server.getXmlRpcServer();
      phm.addHandler("Order", Order.class);
      xmlRpcServer.setHandlerMapping(phm);
      server.start();
      System.out.println("order server started");
    } catch (Exception e) {
      System.err.println("Server exception: " + e);
    }
  }
}
