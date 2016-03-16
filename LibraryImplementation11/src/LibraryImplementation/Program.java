package LibraryImplementation;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.mysql.fabric.xmlrpc.base.Data;

/**
 * 
 * @author Natalia
 *
 */

public class Program {

	
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to library");
		System.out.println("All our readers:");
		ReaderDB readers = new ReaderDB();
		readers.selectAllReaders();
		/*System.out.println("All our books:");
		BookDB books = new BookDB();
		books.selectAllBooks();
		while (true) {
			System.out.println("Choose an action:\n - add Book (press 1) \n - add Reader (press 2) "
					+ "\n - update Reader's profile (press 3) \n - delete reader (press 4)\n - get a book (press 5)"
					+ "\n - take a book (press 6) \n - show who got books (press 7) \n"
					+ " - show who took books (press 8)\n - exit (press 9)");
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter your choice:");
			int decision = keyboard.nextInt();
			if (decision == 1) {
				System.out.println("Enter book title: ");
				String bookTitle = keyboard.next();
				System.out.println("Enter book id: ");
				int bookId = keyboard.nextInt();
				System.out.println("Enter book price: ");
				double bookPrice = keyboard.nextDouble();
				Book newBook = new Book(bookId, bookTitle, bookPrice);
				books.insertBook(newBook);
				if (!books.isBookWithThisIdStatus(bookId)) {
					System.out.println("Book " + bookTitle + " already exist in library. Try again");
				} else {
					System.out.println("Book " + bookTitle + " added to library");
				}
			} else if (decision == 2) {
				System.out.println("Enter reader's name: ");
				String readerName = keyboard.next();
				System.out.println("Enter reader's address: ");
				String readerAddress = keyboard.next();
				System.out.println("Enter reader's phone: ");
				int readerPhone = keyboard.nextInt();
				System.out.println("Enter reader's id: ");
				String readerId = keyboard.next();
				System.out.println("Enter reader's birtsday: ");
				String readerBirtsday = keyboard.next();
				Reader newReader = new Reader(readerName, readerAddress, readerPhone, readerBirtsday, readerId);
				readers.insertReader(newReader);
				if (!readers.selectByIdStatus(readerId)) {
					System.out.println("Reader " + readerName + " added to library");
				} else {
					System.out.println("Reader with this id already exist in library. Try again");
				}
			} else if (decision == 3) {
				System.out.println("For updating your profile enter your reader card number:");
				String readerId = keyboard.next();
				System.out.println("Enter your new address: ");
				String readerNewAddress = keyboard.next();
				System.out.println("Enter your new phone:");// 
				int readerNewPhone = keyboard.nextInt();
				readers.updateReaderProfileById(readerId, readerNewAddress, readerNewPhone);
			}  else if (decision == 4) {
				System.out.println("For deleting your profile enter your reader card number:");
				String readerId = keyboard.next();
				readers.deleteReaderByReaderCard(readerId);
				readers.selectAllReaders();
			} else if (decision == 5) {
				System.out.println("Enter book title: ");
				String bookTitle = keyboard.next();
				GetDB getBook = new GetDB();
				System.out.println("Enter reader card: ");
				String readerId = keyboard.next();
				getBook.getBook(readerId, bookTitle);
				System.out.println("Done");
				getBook.selectAll();
			} else if (decision == 6) {
				System.out.println("Enter book title: ");
				String bookTitle = keyboard.next();
				TakeDB takeBook = new TakeDB();
				System.out.println("Enter reader card: ");
				String readerId = keyboard.next();
				takeBook.takeBook(readerId, bookTitle);
				System.out.println("Done");
				takeBook.selectAll();
			} else if (decision == 7) {
				GetDB getBook = new GetDB();
				getBook.selectAll();
			} else if (decision == 8) {
				TakeDB takeBook = new TakeDB();
				takeBook.selectAll();
			} else if (decision == 9) {
				System.out.println("Good luck. Hope, we could help you. See you later.");
				break;
			}
		}*/
	}
	
	
}
