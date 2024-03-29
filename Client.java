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
    String reply;
    String welcome_reply;
    String request;

    List<Object> params = new ArrayList<Object>();
    params.add(1);

    try {
      welcome_reply = (String) client.execute("Front.welcome", params);
      System.out.println(welcome_reply);
    } catch (Exception e) {
      System.err.println("Client exception: " + e);
      return;
    }
    Scanner scanner;
    //  Start accepting commands
    while (true) {
      System.out.println("\nHere are the available actions\nsearch [genre]\nlookup [item_number]\nbuy [item_number]\n");
      System.out.print("What would you like to do? :  ");
      scanner = new Scanner(System.in);
      request = scanner.nextLine();
      String[] line = request.split(" ", 2);
      if(line.length == 0) {
        continue;
      }
      params.clear();
      params.add(line[0]);
      params.add(line[1]);

      try {
        reply = (String) client.execute("Front.HandleRequest", params);
        System.out.println(reply);
      } catch (Exception e) {
        System.err.println("Client exception: " + e);
        return;
      }
    }
  }

}
