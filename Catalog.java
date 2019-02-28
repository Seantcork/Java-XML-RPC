import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask; 

   /*
    * an XMLP server program for managing inventroy and returning book information
    */
public class Catalog{


	public static HashMap<Integer, Book> Booklist = new HashMap<Integer, Book>();

   /*
    * Use: Get books cataloged under a specific topic id
    * Parameter: topic being requested
    * return: list of bookinfo in the form of strings
    */
	public ArrayList<String> query_by_topic(String topic){
		ArrayList<String> response = new ArrayList<String>();
		ArrayList<Book> books = new ArrayList<Book>();
		for(Book x: Booklist.values()){
			if(x.topic.equals(topic)){
				books.add(x);
			}
		}
		if(books.size() == 0) {
			response.add("No books found for this topic.");
			return response;
		}
		// Maybe formatter
		for(Book y: books){
			response.add((y.title + " By: " + y.author + " Genre: " + y.topic +
			" Item num: " + y.item_num + " Quantity " +  y.quantity));
		}
		return response;
	}

   /*
    * Use: Get the book info for a specific book id
    * Parameter: ID being requested
    * return: bookinfo from Book class
    */
	public ArrayList<String> query_by_item(String item_num){
		ArrayList<String> response = new ArrayList<String>();
		System.out.println(item_num);
		int num = Integer.parseInt(item_num);
		Book book = Booklist.get(num);
		if(book == null){
			response.add("Item does not exist");
			return response;
		} else {
			response.add(book.title);
			response.add(book.topic);
			response.add(book.author);
			response.add(String.valueOf(book.item_num));
			response.add(String.valueOf(book.quantity));
			return response;
		}

	}
   /*
    * Use: Decrement book quantity by 1
    * Parameter: item num of book being bought
    * return: must return something for excecution
    */
	public String update(String item_num) {
		int num = Integer.parseInt(item_num);
		Book book = Booklist.get(num);
		book.reduce();
		return "success";
	}

   /*
    * Use: create bookstore adn fill it
    */
	public static void createBookstore(){
		Booklist = new HashMap<Integer, Book>();
		Book book = new Book("Dune", "sci-fi", "Frank Herbert", 101, 10);
		Booklist.put(book.item_num, book);
		book = new Book("Foundation", "sci-fi", "Issac Assimov", 102, 10);
		Booklist.put(book.item_num, book);
		book = new Book("The Origin of Species", "sci-fi", "Some old Kook", 103, 10);
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
		book = new Book("Lubrication Firsts", "Self Help Books For Robots", "Self Help Bot", 302, 10);
		Booklist.put(book.item_num, book);
		book = new Book("How to fix your circuits", "Self Help Books For Robots", "Robot Company", 303, 10);
		Booklist.put(book.item_num, book);

		System.out.println("createBookstore");
	}
   /*
    * Use: print entire inventory
    */
	public static void printBookstore(){
		for(Book x: Booklist.values()){
			x.info();
		}
	}
   /*
    * Use: run a threadsafe restock of all books every 30s
    */
	public static synchronized void restock(){
		TimerTask repeatedTask = new TimerTask() {
			public void run(){
				for(Book x: Booklist.values()){
					x.restock();
				}
        	}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(repeatedTask, 30000, 30000);  
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
	      restock();
	    } catch (Exception e) {
	      System.err.println("Server exception: " + e);
	    }

	  }
}

