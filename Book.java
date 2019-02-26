

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
			quantity -= 1;
		}
	}

	public void restock() {
		quantity = restock_val;
	}

	public void info() {
		System.out.print("Title: " + title + " Author: " + author + " Topic: " + topic + " Item_Id: " +
		 item_num + " Quantity in Stock: " + quantity);

	}
}