package LibraryImplementation;

public class Book {
	
	private String bookId;
	private String bookTitle;
	private double bookPrice;
	
	Book(String aBookId, String  aBookTitle, double aBookPrice) {
		bookId = aBookId;
		bookTitle = aBookTitle;
		bookPrice = aBookPrice;
	}

	public String getBookId() {
		return bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	
}
