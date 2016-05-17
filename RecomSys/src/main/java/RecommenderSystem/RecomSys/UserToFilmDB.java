package RecommenderSystem.RecomSys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserToFilmDB {
	
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/Films";

	
	public boolean addToFavourites(Film aFilm, User aUser) throws ClassNotFoundException, SQLException {
		if (aFilm != null && aUser != null) {
			if (!checkUser(aUser.getUserName())) {
			   Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
			   stmt = conn.createStatement();
			   String insertSQL = "INSERT INTO UserPriority(userId, userName, filmFirstId, firstFilm,"
			   		+ " filmSecondId, secondFilm, filmThirldId, thirldFilm) "
			   		+ "VALUES ('" + aUser.getUserId() + "', '" + aUser.getUserName() + "', " + aFilm.getFilmId() + ", '" + aFilm.getFilmTitle() + ""
		                 		+ "', '"+ aFilm.getFilmId() + "', " + aFilm.getFilmTitle() + "', " + aFilm.getFilmId() + "', " + aFilm.getFilmTitle() +"')";
			   stmt.executeUpdate(insertSQL);   
			}
		} else {
		return false;  
		}
		return true;
	}
	
	public UserToFilm selectUserByName(String aName) throws ClassNotFoundException, SQLException {
		FilmDB filmDB = new FilmDB();
  	  	ArrayList<Film> userChoice = new ArrayList<Film>();
		UserToFilm userToFilm = null;
		   Connection conn = null;
		   Statement stmt = null;
		   Class.forName("com.mysql.jdbc.Driver");
		   conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
		   stmt = conn.createStatement();
		   String selectSQL = "SELECT * FROM UserPriority";
		   ResultSet rs = stmt.executeQuery(selectSQL);
		   while(rs.next()) {	
			   int id = rs.getInt("priorityId");
			   String name = rs.getString("userName");
			   String firstFilmTitle = rs.getString("firstFilm");
			   Film firstFilm = filmDB.selectFilm(firstFilmTitle);
			   String secondFilmTitle = rs.getString("secondFilm");
			   Film secondFilm = filmDB.selectFilm(secondFilmTitle);
			   String thirldFilmTitle = rs.getString("thirldFilm");
			   Film thirldFilm = filmDB.selectFilm(thirldFilmTitle);
			   userChoice.add(firstFilm);
			   userChoice.add(secondFilm);
			   userChoice.add(thirldFilm);
	          if (name.equals(aName)) {
	        	userToFilm = new UserToFilm(name, userChoice);
	        	  break;
	          }
	       }
		   rs.close();
	          if (userToFilm != null) {
	        	  return userToFilm;
	          } else {
	        	  return null;
	          }
	}
	
	private boolean checkUser(String aName) throws SQLException, ClassNotFoundException {
		 String name = "";
			   Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
			   stmt = conn.createStatement();
			   String selectSQL = "SELECT userName FROM UserPriority";
			   ResultSet rs = stmt.executeQuery(selectSQL);
			   while(rs.next()) {	
		          name = rs.getString("userName");
		          if (name.equals(aName)) {
		        		  break;
		        	  }
		       }
			   rs.close();
			   if (name.equals(aName)) {
	        		  return true;
	           } else {
	        	   return false; 
	          }
	}
}
