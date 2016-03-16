package LibraryImplementation;

public class Book {
	
	private int bookId;
	private String bookTitle;
	private double bookPrice;
	
	Book(int aBookId, String  aBookTitle, double aBookPrice) {
		bookId = aBookId;
		bookTitle = aBookTitle;
		bookPrice = aBookPrice;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	
}
