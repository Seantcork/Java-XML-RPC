import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;


public class Order{

public Integer[] sumAndDifference(int x, int y) {
    System.out.println("calling sumAndDifference(" + x + ", " + y + ")");
    Integer[] array = new Integer[2];
    array[0] = x + y;
    array[1] = x - y;
    return array;
  }





public static void main(String[] args) {
    try {
      int x = 1;
      System.out.println(x);
      PropertyHandlerMapping phm = new PropertyHandlerMapping();
      XmlRpcServer xmlRpcServer;
      WebServer server = new WebServer(8123);
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
