package LibraryImplementation;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program1 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Reader newReader = new Reader("Vasia Pupkin", "Amosova str", 897, "2015-12-12", "2329");
		ReaderDB readers = new ReaderDB();
//		readers.insertReader(newReader);
//		readers.updateReaderProfileById("9867432323", "Skovorodu str", +38096);
//		Reader testReader = new Reader("Olha Danuliv", "Peremohy str", 9000, "2010-12-12", "05864");
//		readers.insertReader(testReader);
//		//readers.updateReaderProfileByName("Olha Danuliv", "Bashana str", 78654);
		//readers.selectAllReaders();
		
		BookDB bd = new BookDB();
		bd.selectAllBooks();
	}
	
	
	

}
