import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import java.util.*;



public class Catalog{


	public static HashMap<Integer, Book> Booklist;

	public String welcome(int x, int y) {
	  System.out.println("calling welcome");
	  String answer = "WELCOME TO THE BOOKSTORE\nHave a look around\n";
	  return answer;
	}

	public String query_by_topic(String topic){
		String response;
		ArrayList<Book> books = new ArrayList<Book>();
		for(Book x: Booklist.values()){
			if(x.topic.equals(topic)){
				books.add(x);
			}
		}

		for(Book y: books){
			response += "Title: " + y.title + " Topic: " + y.topic + " Author: " + y.author +
			" Item num: " + y.item_num + " Quantity " +  y.quantity + "\n";
		}
		return response;
	}

	public String query_by_item(String item_num){
		String response;
		System.out.println(item_num);
		int num = Integer.parseInt(item_num);
		Book book = Booklist.get(num);
		if(book == null){
			response = "item doesnt exit\n";
			return response;
		}
		else{
			response += "Title: " + book.title + " Topic: " + book.topic + " Author: " + book.author +
			" Item num: " + book.item_num + " Quantity " + book.quantity + "\n";
			return response;
		}

	}

	public void update(int item_num, int quantity){
		Book book = Booklist.get(item_num);
		for(int i = 0; i < quantity; i++){
			book.reduce();
		}
	}

	public static void createBookstore(){
		Booklist = new HashMap<Integer, Book>();
		Book book = new Book("Dune", "sci-fi", "Frank Herbert", 101, 10);
		Booklist.put(book.item_num, book);
		book = new Book("Foundation", "sci-fi", "Issac Assimov", 102, 10);
		Booklist.put(book.item_num, book);
		book = new Book("Enders Game", "sci-fi", "Orson Scott Card", 103, 10);
		Booklist.put(book.item_num, book);
		book = new Book("How to get the Druid", "Elvish Erotic Novels", "Tree Man", 201, 10);
		Booklist.put(book.item_num, book);
		book = new Book("Flower Power", "Elvish Erotic Novels", "Daisy Herb", 202, 10);
		Booklist.put(book.item_num, book);
		book = new Book("Are you happy to Tree", "Elvish Erotic Novels", "Tree Man", 203, 10);
		Booklist.put(book.item_num, book);
		book = new Book("Natures Neat", "Elvish Erotic Novels", "Fransisco Naturno", 204, 10);
		Booklist.put(book.item_num, book);
		book = new Book("Finding the right gears", "Self Help Books For Robots", "Automota Man", 301, 10);
		Booklist.put(book.item_num, book);
		book = new Book("Lubrication Firsts", "Self Help Books For Robots", "Self Help Robot", 302, 10);
		Booklist.put(book.item_num, book);
		book = new Book("How to fix your circuits", "Self Help Books For Robots", "Robot Company", 303, 10);
		Booklist.put(book.item_num, book);

		System.out.println("createBookstore");
	}

	public static void printBookstore(){
		for(Book x: Booklist.values()){
			x.info();
		}
	}

	public static void main(String[] args) {
	    try {
	      System.out.println("Started Catalogue Server");
	      PropertyHandlerMapping phm = new PropertyHandlerMapping();
	      XmlRpcServer xmlRpcServer;
	      WebServer server = new WebServer(8123);
	      xmlRpcServer = server.getXmlRpcServer();
	      phm.addHandler("Catalog", Catalog.class);
	      xmlRpcServer.setHandlerMapping(phm);
	      server.start();
	      System.out.println("order server started");
	      createBookstore();
	      printBookstore();
	    } catch (Exception e) {
	      System.err.println("Server exception: " + e);
	    }

	  }
}



/*Books

Title: Dune




*/