package LibraryImplementation;

import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.Statement;

import com.mysql.jdbc.*;
import java.sql.Connection;


public class BookDB {
	
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/Library";

	   static final String USER = "root";
	   static final String PASS = "root";

	
	   public void insertBook(Book aBook) throws ClassNotFoundException, SQLException {
		   String bid = aBook.getBookId();
		   String bTitle = aBook.getBookTitle();
		   double bPrice = aBook.getBookPrice();
		   if (isBookWithThisIdStatus(bid)) {
			   System.out.println("Sorry, book with this id is in our library");
		   } else {
			   Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   stmt = conn.createStatement();
			   String insertSQL = "INSERT INTO Book VALUES ('" + bid +"','"+ bTitle +"','"+ bPrice +"')";
			   stmt.executeUpdate(insertSQL);
		   }
	   }

	   
	   public void selectAllBooks() throws ClassNotFoundException, SQLException {
		   Connection conn = null;
		   Statement stmt = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT * FROM Book";
	       ResultSet rs = stmt.executeQuery(selectSQL);
		   while (rs.next()) {
			   int id  = rs.getInt("bid");
		       String title = rs.getString("btitle");
		       int price = rs.getInt("bprice");
		       if (!title.equals("")) {
		    	   System.out.print("Id: " + id);
			       System.out.print(" title : " + title);
			       System.out.print(" price: " + price + " UAH\n");
		        } 
		      }
		   rs.close();
	   }
	   
	   public String selectBookByTitle(String aTitle) throws ClassNotFoundException, SQLException {
		   Connection conn = null;
		   Statement stmt = null;
		   int id = 0;
		   String title = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT * FROM Book WHERE btitle = '" + aTitle + "'";
		   ResultSet rs = stmt.executeQuery(selectSQL);
		   while (rs.next()) {
			    id  = rs.getInt("bid");
		        title = rs.getString("btitle");
		       int price = rs.getInt("bprice");
		       if (title.length() > 0) {
		    	   System.out.print("Id: " + id);
			       System.out.print(" title : " + title);
			       System.out.print(" price: " + price);
		        } else {
			       System.out.print("Book with title = " + aTitle + " is not in this library");
		         }
		      }
		   rs.close();
		   if (aTitle.equals(title)) {
			   return title; 
		   } else {
			   return "This book is not in our library. Sorry.";
		   }
		   
	   }
	   
	   public boolean isBookWithThisIdStatus(String anId) throws ClassNotFoundException, SQLException {
		   Connection conn = null;
		   Statement stmt = null;
		   String id = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT bid FROM Book WHERE bid = '" + anId + "'" ;
		   ResultSet rs = stmt.executeQuery(selectSQL);
		   while (rs.next()) {
			   id  = rs.getString("bid");		      
		      }
		    rs.close();
		    if (anId.equals(id)) {
		    	return true;
		    } else {
		    	return false;
		    }
	   }
	   
	  public String selectBookById(String anId) throws ClassNotFoundException, SQLException {
		  String id = null;  
		  String title = null;
		  	Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   stmt = conn.createStatement();
			   String selectSQL = "SELECT bid FROM Book WHERE bid = '" + anId + "'" ;
			   ResultSet rs = stmt.executeQuery(selectSQL);
			   while (rs.next()) {
				   id  = rs.getString("bid");
			    }
			    rs.close();
			    if (anId.equals(id)) {
			    	   return id;
			     } else {
				       return null;
			     }
		   }
}		 