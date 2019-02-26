import java.util.List; 
import java.util.ArrayList; 
import java.net.URL;     
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.util.Scanner;

/**
 * A simple example XML-RPC client program.
 */
public class Client {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: [server]");
      return;
    }
    String hostname = args[0];

    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    XmlRpcClient client = null;
    
    try {
      config.setServerURL(new URL("http://" + hostname + ":8124"));
      client = new XmlRpcClient();
      client.setConfig(config);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
      return;
    }

    // Get FrontEnd Welcome
    Object[] reply;
    String request;

    List<Object> params = new ArrayList<Object>();
    params.add(1);
    params.add(2);


    // try {
    //   System.out.println("tryint to execute welcome");
    //   reply = (Object[]) client.execute("Sample.sum", params);
    //   System.out.println(reply);
    // } catch (Exception e) {
    //   System.err.println("Client exception: " + e);
    //   return;
    // }

    try {
      System.out.println("trying to execute welcome");
      reply = (Object[]) client.execute("Front.welcome", params);
      System.out.println(reply);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
      return;
    }
    // Scanner scanner;
    // //  Start accepting commands
    // while (true) {
    //   System.out.println("Enter your username: ");
    //   scanner = new Scanner(System.in);
    //   request = scanner.nextLine();
      
    //   System.out.println("Waiting for Request");
    //   try {
    //     reply = (Object[]) client.execute("Front.handleRequest", params);
    //   } catch (Exception e) {
    //     System.err.println("Client exception: " + e);
    //     return;
    //   }
    // }
  }

}
