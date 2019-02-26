import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import java.util.*;



public class Catalogue{


	public static HashMap<Integer, Book> Booklist = new HashMap<Integer, Book>();

	public String welcome(int x, int y) {
	  System.out.println("calling welcome");
	  String answer = "WELCOME TO THE BOOKSTORE\nHave a look around\n";
	  return answer;
	}

	public ArrayList<Book> query_by_topic(String topic){
		ArrayList<Book> books = new ArrayList<Book>();
		for(Book x: Booklist.values()){
			if(x.topic.equals(topic)){
				books.add(x);
			}
		}
		return books;
	}

	public Book query_by_item(int item_num){
		Book book = Booklist.get(item_num);
		if(book == null){
			System.out.println("item doesnt exist");
			return null;
		}
		else{
			return book;
		}

	}

	public void update(int item_num, int quantity){
		Book book = Booklist.get(item_num);
		for(int i = 0; i < quantity; i++){
			book.reduce();
		}
	}

	public static void createBookstore(){
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
	}

	public static void main(String[] args) {
	    try {
	      System.out.println("Started Catalogue Server");
	      PropertyHandlerMapping phm = new PropertyHandlerMapping();
	      XmlRpcServer xmlRpcServer;
	      WebServer server = new WebServer(8123);
	      xmlRpcServer = server.getXmlRpcServer();
	      phm.addHandler("Catalogue", Catalogue.class);
	      xmlRpcServer.setHandlerMapping(phm);
	      server.start();
	      System.out.println("order server started");
	    } catch (Exception e) {
	      System.err.println("Server exception: " + e);
	    }
	    createBookstore();

	  }
}



/*Books

Title: Dune




*/