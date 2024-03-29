/*

Book class that keeps track of books in our bookstore. Supports
a constructor and a function that decrements the quantity of books available
and a function that restocks the book in the bookstore.


*/

class Book {
	public static final int restock_val = 10;
	String title;
	String topic;
	String author;
	int item_num;
	int quantity;

	public Book(String title, String topic, String author, int item_num, int quantity) {
		this.title = title;
		this.topic = topic;
		this.author = author;
		this.item_num = item_num;
		this.quantity = quantity;
	}

	public void reduce() {
		if (quantity <= 0) {
			System.out.println("Error: trying to reduce something thats out of stock");
		}
		else {
			this.quantity -= 1;
		}
	}

	public void restock() {
		this.quantity += restock_val;
	}

	public void info() {
		System.out.print("Title: " + title + " Author: " + author + " Topic: " + topic + " Item_Id: " +
		 item_num + " Quantity in Stock: " + quantity +"\n\n");

	}
}
