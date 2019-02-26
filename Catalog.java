import org.apache.xmlrpc.webserver.WebServer; 
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import java.util.*;


public class Catalog{


	HashMap<Integer, Book> Booklist = new HashMap<Integer, Book>();




	public ArrayList<Book> getAllTopics(String topic){
		ArrayList<Book> books = new ArrayList<Book>();
		for(Book x: Booklist.values()){
			if(x.topic.equals(topic)){
				books.add(x);
			}
		}
		return books;
	}

	public Book getBook(int item_num){
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

	public void createBookstore(){
		Book book = new Book("Dune", "sci-fi", "Frank Herbert", 101, 10);
		Booklist.put(book.item_num, book);
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



/*Books

Title: Dune




*/