package RecommenderSystem.RecomSys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class UserDB {

	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/Films";

	UserDB() {
		
	}
	
	
	public boolean checkUser(String aName, String aPassword) throws ClassNotFoundException, SQLException {
		User theUser = null;
		  Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
			   stmt = conn.createStatement();
			   String selectSQL = "SELECT * FROM User";
			   ResultSet rs = stmt.executeQuery(selectSQL);
			   while(rs.next()) {	
				  int id = rs.getInt("userId");
		          String name = rs.getString("userName");
		          String password = rs.getString("userPassword");
		          if (name.equals(aName)) {
		        	  if (password.equals(aPassword)) {
		        		  theUser = new User(id, name, password);
		        		  break;
		        	  }
		          }
			   }
			   rs.close();
			   if (theUser != null) {
				   return true;
			   } else {
				   return false; 
			   }
	}
	
	public User selectUser(String aName, String aPassword) throws ClassNotFoundException, SQLException {
		User theUser = null;
		  Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
			   stmt = conn.createStatement();
			   String selectSQL = "SELECT * FROM User";
			   ResultSet rs = stmt.executeQuery(selectSQL);
			   while(rs.next()) {	
				  int id = rs.getInt("userId");
		          String name = rs.getString("userName");
		          String password = rs.getString("userPassword");
		          if (name.equals(aName)) {
		        	  if (password.equals(aPassword)) {
		        		  theUser = new User(id, name, password);
		        		  break;
		        	  }
		          }
			   }
			   rs.close();
			   if (theUser != null) {
				   return theUser;
			   } else {
				   return null; 
			   }
	}
	
	
	// user can edit his password
	
	public boolean editUserInfo(User aUser, String aPassword) throws ClassNotFoundException, SQLException {
		if (aUser == null) {
			  return false;
		  } else {
			  Connection conn = null;
			  Statement stmt = null;
			  Class.forName("com.mysql.jdbc.Driver");
			  conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
			  stmt = conn.createStatement();
			  String updateSQL = "UPDATE User SET userPassword = '?' WHERE userId = '?'";
			  PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(updateSQL);
				preparedStatement.setInt(1, aUser.getUserId());
				preparedStatement.setString(2, aPassword);
			  stmt.executeUpdate(updateSQL);
			  System.out.println("Done");
		  }
		
		return true;
	}
	
	
}
