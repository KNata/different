package LibraryImplementation;

import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.*;
import java.sql.Connection;
import java.util.Date;


public class ReaderDB {
	
	   private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   private static final String DB_URL = "jdbc:mysql://localhost/Library";

	   private static final String USER = "root";
	   private static final String PASS = "root";
	   
	 
	 
	
	   public void insertReader(Reader aReader) throws ClassNotFoundException, SQLException {
		   String name = aReader.getReaderName();
		   String address = aReader.getReaderAddress();
		   int phone = aReader.getReaderPhone();
		   String bDay = aReader.getReaderBDay();
		   String id = aReader.getReaderCard();
		   Connection conn = null;
		   Statement stmt = null;
		   if (selectByIdStatus(id)) {
			   System.out.println("Sorry, but reader with this id is in our library. Try again");
		   } else {
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   stmt = conn.createStatement();
			   String insertSQL = "INSERT INTO Reader VALUES ('" + name + "', '" + address + "', " + phone + ", '" + bDay + ""
		                 		+ "', '"+ id +"')";
			   stmt.executeUpdate(insertSQL);
			   System.out.println("Done");
		   }
	   }
	   
	   public void selectAllReaders() throws ClassNotFoundException, SQLException {
		   Connection conn = null;
		   Statement stmt = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT * FROM Reader" ;
		   ResultSet rs = stmt.executeQuery(selectSQL);
		   while (rs.next()) {
			   String name  = rs.getString("rname");
		       String address = rs.getString("raddress");
		       int phone = rs.getInt("rphone");
		       String bday = rs.getString("rbirsday");
		       int id = rs.getInt("rreadercard");
		      
		    	   System.out.print("Name: " + name);
			       System.out.print(" address : " + address);
			       System.out.print(" phone: " + phone);
			       System.out.println(" birthday: " + bday);
			       System.out.println(" reader card: " + id + ".");
		       
		      }
		   rs.close();
	   }
	   
	   public void selectReaderByName(String aName) throws ClassNotFoundException, SQLException {
		   Connection conn = null;
		   Statement stmt = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT rname, raddress, rphone, rbirsday, rreadercard FROM Reader WHERE rname = '" + aName + "'" ;
		   ResultSet rs = stmt.executeQuery(selectSQL);
		   while (rs.next()) {
			   String name  = rs.getString("rname");
		       String address = rs.getString("raddress");
		       int phone = rs.getInt("rphone");
		       String bday = rs.getString("rbirsday");
		       int id = rs.getInt("rreadercard");
		       if (!name.equals("")) {
		    	   System.out.print("Name: " + name);
			       System.out.print(" address : " + address);
			       System.out.print(" phone: " + phone);
			       System.out.println(" birthday: " + bday);
			       System.out.println(" reader card: " + id + ".");
		        } else {
			       System.out.print("Reader with name = " + aName + " not registered in this library");
		         }
		      }
		   rs.close();
	   }
	   
	  public boolean selectByIdStatus(String anId) throws ClassNotFoundException, SQLException {
		  int id = 0;
		  Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   stmt = conn.createStatement();
			   String selectSQL = "SELECT rname, raddress, rphone, rbirsday, rreadercard FROM Reader WHERE rreadercard = '" + anId + "'" ;
			   ResultSet rs = stmt.executeQuery(selectSQL);
			   while(rs.next()) {		        
		          id = rs.getInt("rreadercard");
			    }
			    rs.close();
			    if (id != 0) {
			    	return true;
			    } else {
			    	return false;
			    }
	 }
		  
	  
	  
	  
	  public void updateReaderProfileById(String iid, String anAddress, int aPhone) throws ClassNotFoundException, SQLException {
		  if (selectByIdStatus(iid)) {
			  Connection conn = null;
			  Statement stmt = null;
			  Class.forName("com.mysql.jdbc.Driver");
			  conn = DriverManager.getConnection(DB_URL, USER, PASS);
			  stmt = conn.createStatement();
			  String updateSQL = "UPDATE Reader SET raddress = '" + anAddress + "', rphone = "+ aPhone +" WHERE rreadercard in ('" + iid + "')";
			  stmt.executeUpdate(updateSQL);
			  System.out.println("Done");
		  } else {
			  System.out.println("Reader with this id is not registered in this library");
		  }
	  }
	 
	  
	  public void deleteReaderByReaderCard(String anId) throws ClassNotFoundException, SQLException {
		  if (selectByIdStatus(anId)) {
				   Connection conn = null;
				   Statement stmt = null;
				   Class.forName("com.mysql.jdbc.Driver");
				   conn = DriverManager.getConnection(DB_URL, USER, PASS);
				   stmt = conn.createStatement();
				   String selectSQL = "SELECT rname, raddress, rphone, rbirsday, rreadercard FROM Reader WHERE rreadercard = '" + anId + "'" ;
				   stmt.executeQuery(selectSQL);
				   stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				   ResultSet rs = stmt.executeQuery(selectSQL);
				   while (rs.next()) {
					   String name  = rs.getString("rname");
				       String address = rs.getString("raddress");
				       int phone = rs.getInt("rphone");
				       String bday = rs.getString("rbirsday");
				       int id = rs.getInt("rreadercard");
					   rs.deleteRow();
					   System.out.println("Reader with id " + anId + " deleted");
				   }
		  } else {
			   System.out.println("Wrong id. Try again");
		   }
	  }
}
