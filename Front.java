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

  static String order_server = "";
  static String catalog_server = "";

  XmlRpcClient new_client(String port_num, String server) {
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    XmlRpcClient client = null;
    try {
      config.setServerURL(new URL("http://" + server + ":" + port_num));
      client = new XmlRpcClient();
      client.setConfig(config);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
      return null;
    }
    return client;
  }

  public String HandleRequest(String function, String arg) {


    ArrayList<String> params = new ArrayList<String>();
    params.add(arg);

    if(function.equals("search")){
      Object[] books;
      String reply = "";
      XmlRpcClient client = new_client("8123", catalog_server);
      try {
        books = (Object[]) client.execute("Catalog.query_by_topic", params);
      } catch (Exception e) {
        System.err.println("Client exception: " + e);
        return "Error in search";
      }
      for(Object info: books) {
        reply += info + "\n";
      }
      return reply;
    }

    else if(function.equals("lookup")) {
      Object[] bookinfo;
      String reply = "";
      XmlRpcClient client = new_client("8123", catalog_server);
      try {
        bookinfo = (Object[]) client.execute("Catalog.query_by_item", params);
      } catch (Exception e) {
        System.err.println("Client exception: " + e);
        return "Error in lookup";
      }
      if(bookinfo.length == 1) {
        return bookinfo[0].toString();
      }

      for(Object info: bookinfo) {
        reply = "Title: " + bookinfo[0] + 
                " Topic: " + bookinfo[1] + 
                " Author: " + bookinfo[2] +
                " Item num: " + bookinfo[3] + 
                " Quantity: " +  bookinfo[4];
      }
      reply += "\n";
      return reply;

    }

    else if(function.equals("buy")) {
      Object confirmation;
      XmlRpcClient client = new_client("8125", order_server);
      try {
        confirmation = client.execute("Order.buy", params);
      } catch (Exception e) {
        System.err.println("Client exception: " + e);
        return "Error in buy 2";
      }
      return confirmation.toString();
    } 
    else {
      return "Command not recognized";
    }
  }

  public String welcome(int nothing) {
    String answer = "----------------------------------------------------------\n" +
    "----------------------------------------------------------\n" + 
    "-----------------WELCOME TO THE BOOKSTORE-----------------\n" +
    "--------------------Have a look around--------------------\n" + 
    "----------------------------------------------------------\n" + 
    "----------------------------------------------------------\n";
    answer+= "Here are the topics that we are currently Stocking:\n";
    answer += "    sci-fi\n" + "    Elvish Erotic Novels\n" + "    Self Help Books For Robots\n";
    return answer;
  }


  public static void main(String[] args) {
    try {
       if (args.length != 2) {
        System.out.println("Usage: [order server] [catalog server]");
        return;
      }
      catalog_server = args[1];
      order_server = args[0];

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
