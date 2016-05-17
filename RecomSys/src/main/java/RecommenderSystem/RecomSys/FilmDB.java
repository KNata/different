package RecommenderSystem.RecomSys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class FilmDB {
	
	private ArrayList<Film> filmList;
	
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/Films";


	FilmDB() {
		filmList = new ArrayList<Film>();
	}
	
	
	public boolean isFilmInDB(String aTitle) throws SQLException, ClassNotFoundException {
		Film theFilm = null;
		  Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
			   stmt = (Statement) conn.createStatement();
			   String selectSQL = "SELECT * FROM Film";
			   ResultSet rs = stmt.executeQuery(selectSQL);
			   while(rs.next()) {	
				  int id = rs.getInt("filmId");
		          String title = rs.getString("filmTitle");
				  String filmGenre = rs.getString("filmGenre");
				  int filmYearOfCreation = rs.getInt("filmYearOfCreation");
				  double filmRaiting = rs.getDouble("filmRaiting");
				  int filmPositiveAnswers = rs.getInt("filmPositiveAnswers");
				  int filmNegativeAnswers = rs.getInt("filmNegativeAnswers");
		          if (title.equals(aTitle)) {
		        		  theFilm = new Film(id, title, filmGenre, filmYearOfCreation,
		        				  filmRaiting, filmPositiveAnswers, filmNegativeAnswers);
		        		  break;
		        	  }
		          }
			   
			   rs.close();
			   if (theFilm != null) {
				   return true;
			   } else {
				   return false; 
			   }
	}

	
	public ArrayList<Film> selectAllFilms() throws SQLException, ClassNotFoundException {
		Film theFilm = null;
		  Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
			   stmt = (Statement) conn.createStatement();
			   String selectSQL = "SELECT * FROM Film";
			   ResultSet rs = stmt.executeQuery(selectSQL);
			   while(rs.next()) {	
				  int id = rs.getInt("filmId");
		          String title = rs.getString("filmTitle");
				  String filmGenre = rs.getString("filmGenre");
				  int filmYearOfCreation = rs.getInt("filmYearOfCreation");
				  double filmRaiting = rs.getDouble("filmRaiting");
				  int filmPositiveAnswers = rs.getInt("filmPositiveAnswers");
				  int filmNegativeAnswers = rs.getInt("filmNegativeAnswers");
		          theFilm = new Film(id, title, filmGenre, filmYearOfCreation,
		        		filmRaiting, filmPositiveAnswers, filmNegativeAnswers);
		          filmList.add(theFilm);
		        }
			   rs.close();
		return filmList;
	}
	
	public Film selectFilm(String aTitle) throws SQLException, ClassNotFoundException {
		Film theFilm = null;
		  Connection conn = null;
			   Statement stmt = null;
			   Class.forName("com.mysql.jdbc.Driver");
			   conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
			   stmt = (Statement) conn.createStatement();
			   String selectSQL = "SELECT * FROM Film";
			   ResultSet rs = stmt.executeQuery(selectSQL);
			   while(rs.next()) {	
				  int id = rs.getInt("filmId");
		          String title = rs.getString("filmTitle");
				  String filmGenre = rs.getString("filmGenre");
				  int filmYearOfCreation = rs.getInt("filmYearOfCreation");
				  double filmRaiting = rs.getDouble("filmRaiting");
				  int filmPositiveAnswers = rs.getInt("filmPositiveAnswers");
				  int filmNegativeAnswers = rs.getInt("filmNegativeAnswers");
		          if (title.equals(aTitle)) {
		        		  theFilm = new Film(id, title, filmGenre, filmYearOfCreation,
		        				  filmRaiting, filmPositiveAnswers, filmNegativeAnswers);
		        		  break;
		        	  }
		          }
			   
			   rs.close();
			   if (theFilm != null) {
				   return theFilm;
			   } else {
				   return null; 
			   }
	}
	

	public ArrayList<Film> getFilmList() {
		return filmList;
	}
}
