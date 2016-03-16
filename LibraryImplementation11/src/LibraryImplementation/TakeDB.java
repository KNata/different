package LibraryImplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeDB {
	
	   private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   private static final String DB_URL = "jdbc:mysql://localhost/Library";

	   private static final String USER = "root";
	   private static final String PASS = "root";
	
	   public void takeBook(String aReaderCArd, String aBookkTitle) throws ClassNotFoundException, SQLException {
			BookDB books = new BookDB();
			int bookId = books.selectBookByTitle(aBookkTitle);
			ReaderDB readers = new ReaderDB();
			boolean readerIdStatus = readers.selectByIdStatus(aReaderCArd);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");	
			Date theDate = new Date();
			String currentDate = dateFormat.format(theDate);
			if ((readerIdStatus == true) && (bookId != -1)) {
				Connection conn = null;
				Statement stmt = null;
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				String insertSQL = "INSERT INTO TakeBook VALUES(' " + bookId + "', '" + aReaderCArd + "', ' " + currentDate + "')";
				stmt.executeUpdate(insertSQL);
				conn.commit();
			} else {
				System.out.println("Incorrect reader's id or book title. Try again");
			}
		}

	   
	public void selectAll() throws ClassNotFoundException, SQLException {
		   Connection conn = null;
		   Statement stmt = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT * FROM TakeBook" ;
		   ResultSet rs = stmt.executeQuery(selectSQL);
		   while (rs.next()) {
			   int bid  = rs.getInt("bid");
		       String readercard = rs.getString("rreadercard");
		       String date = rs.getString("day");
		       System.out.print("bid: " + bid);
			   System.out.print(" Reader : " + readercard);
			   System.out.print(" Date: " + date + "\n");
		   }
		   rs.close();
	   }
	
	public void selectById(String aReaderId) throws ClassNotFoundException, SQLException {
		   Connection conn = null;
		   Statement stmt = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT * FROM TakeBook WHERE rreadercard = ' " + aReaderId + "'";
		   ResultSet rs = stmt.executeQuery(selectSQL);
		   while (rs.next()) {
			   int bid  = rs.getInt("bid");
		       String readercard = rs.getString("rreadercard");
		       String date = rs.getString("day");
		       System.out.print("bid: " + bid);
			   System.out.print(" Reader : " + readercard);
			   System.out.print(" Date: " + date + "\n");
		   }
		   rs.close();
	   }
	
	
	public boolean takeReaderBookFromLibraryStatus(String aReaderId) throws ClassNotFoundException, SQLException {
		int bid = 0;
		 String readercard = null;
		 String date = null;
		   Connection conn = null;
		   Statement stmt = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT * FROM TakeBook WHERE rreadercard = ' " + aReaderId + "'";
		   ResultSet rs = stmt.executeQuery(selectSQL);
		   while (rs.next()) {
			   bid  = rs.getInt("bid");
		       readercard = rs.getString("rreadercard");
		        date = rs.getString("day");
		   }
		   rs.close();
		   if (readercard != null) {
			   return true;
		   } else {
			   return false;
		   }
	}

}
