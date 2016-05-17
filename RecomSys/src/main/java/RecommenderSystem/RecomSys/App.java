package RecommenderSystem.RecomSys;

import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;


public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	    System.out.println("Enter your login: ");
		Scanner scan= new Scanner(System.in);
	    String name = scan.nextLine();
	    System.out.println("Enter your password: ");
	    String password = scan.nextLine();
	    UserDB userDB = new UserDB();
	    User selectUser = userDB.selectUser(name, password);
	    if (selectUser != null) {
	    	FilmDB filmDB = new FilmDB();
	        ArrayList<Film> filmList = filmDB.selectAllFilms();
	        FilmService filmService = new FilmService();
	        double averageRating = filmService.calculateAverageRating(filmList);
	        double averagePositiveAnsw = filmService.calculateAveragePositiveAnswers(filmList);
	        double averageNegativeAnsw = filmService.calculateAverageNegativeAnswers(filmList);
	        double ratingMeanSquare = filmService.calculateRatingMeanSquare(filmList, averageRating);
	        double posAnswMeanSquare = filmService.calculatePosAnswMeanSquare(filmList, averagePositiveAnsw);
	        double negAnswMeanSquare = filmService.calculateNegAnswMeanSquare(filmList, averageNegativeAnsw);
	        filmService.normalizeFilmCoordinames(filmList, averageRating, ratingMeanSquare, 
	        		averagePositiveAnsw, posAnswMeanSquare, averageNegativeAnsw, negAnswMeanSquare);
	        UserToFilmDB userToFilmDB = new UserToFilmDB();
	        UserToFilm userToFilm =  userToFilmDB.selectUserByName("Natalia");
	        HashMap<String, Double> centroid = filmService.centroid(userToFilm);
	        TreeMap<String, Double> euiler = filmService.euilerDistance(filmList, centroid);
	        List<Map.Entry<String, Double>> entryList = new ArrayList<Map.Entry<String, Double>>(euiler.entrySet());
	        Collections.sort(entryList, new Comparator<Map.Entry<String, Double>>() {
	           
				@Override
				public int compare(Entry<String, Double> o1,
						Entry<String, Double> o2) {
					// TODO Auto-generated method stub
					 return o1.getValue().compareTo(o2.getValue());
				}
	        });
	    	
	    	for (int i = 0; i < entryList.size(); i++) {
			    System.out.println(i+1 + ". " + entryList.get(i).getKey());
	    	}  
	        
	  
	    } else {
		    System.out.println("Invalid username or password. Try again");
	    }
	}

}
