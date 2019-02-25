public static final int restock_val = 10;

class Book {
	String title;
	String topic;
	String author;
	int item_num;
	int qantity;

	public Book(String title, String topic, String author, int item_num, int quantity) {
		title = title;
		topic = topic;
		author = author;
		item_num = item_num;
		quantity = quantity;
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
		System.out.println("hey");

	}
}