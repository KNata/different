package RecommenderSystem.RecomSys;

import java.util.*;

public class FilmService {

	
	FilmService() {
		
	}
	
	
	public double calculateAverageRating(ArrayList<Film> filmList) {
		double rating = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			rating += filmList.get(i).getFilmRaiting();
		}
		double averageRating = rating / filmList.size();
		return averageRating;
	}
	
	public double calculateAveragePositiveAnswers(ArrayList<Film> filmList) {
		double posAnsw = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			posAnsw += filmList.get(i).getFilmPositiveAnswers();
		}
		double averagePosAnswer = posAnsw / filmList.size();
		return averagePosAnswer;
	}
	
	public double calculateAverageNegativeAnswers(ArrayList<Film> filmList) {
		double negAnsw = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			negAnsw += filmList.get(i).getFilmNegativeAnswers();
		}
		double averageNegAnswer = negAnsw / filmList.size();
		return averageNegAnswer;
	}
	
	public double calculateRatingMeanSquare(ArrayList<Film> filmList, double ratingAverage) {
		double rating = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			rating += Math.pow(filmList.get(i).getFilmRaiting(), 2);
		}
		double ratingVariance = (rating / filmList.size()) - Math.pow(ratingAverage, 2);
		double meanSquare = Math.sqrt(ratingVariance);
		return meanSquare;	
	}
	
	public double calculatePosAnswMeanSquare(ArrayList<Film> filmList, double posAnswAverage) {
		double posAnsw = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			posAnsw += Math.pow(filmList.get(i).getFilmPositiveAnswers(), 2);
		}
		double posAnswVariance = (posAnsw / filmList.size()) - Math.pow(posAnswAverage, 2);
		double meanSquare = Math.sqrt(posAnswVariance);
		return meanSquare;	
	}
	
	public double calculateNegAnswMeanSquare(ArrayList<Film> filmList, double negAnswAverage) {
		double negAnsw = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			negAnsw += Math.pow(filmList.get(i).getFilmNegativeAnswers(), 2);
		}
		double posAnswVariance = (negAnsw / filmList.size()) - Math.pow(negAnswAverage, 2);
		double meanSquare = Math.sqrt(posAnswVariance);
		return meanSquare;	
	}
	
	public void normalizeFilmCoordinames(ArrayList<Film> filmList,
			double averageRating, double meanRatingSquare, double averagePosAnsw,
			double meanPosAnswSquare, double averageNegAnsw, double meanNegAnswSquare) {
		
		double tempRat = 0.0;
		double tempPosAnsw = 0.0;
		double tempNegAnsw = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			tempRat = (filmList.get(i).getFilmRaiting() - averageRating) / meanRatingSquare;
			tempPosAnsw = (filmList.get(i).getFilmPositiveAnswers() - averagePosAnsw) / meanPosAnswSquare;
			tempNegAnsw = (filmList.get(i).getFilmNegativeAnswers() - averageNegAnsw) / meanNegAnswSquare;
			filmList.get(i).setFilmRaiting(tempRat);
			filmList.get(i).setFilmPositiveAnswers(tempPosAnsw);
		}
	}
	
	public HashMap<String, Double> centroid(UserToFilm aUserToFilm) {
		ArrayList<Film> tempList = aUserToFilm.getUserChoice();
		double xCoord = 0.0;
		double yCoord = 0.0;
		double zCoord = 0.0;
		double centrX = 0.0;
		double centrY = 0.0;
		double centrZ = 0.0;
		for (int i = 0; i < tempList.size(); i++) {
			 xCoord += tempList.get(i).getFilmRaiting();
			 centrX = xCoord / tempList.size();
			 yCoord += tempList.get(i).getFilmPositiveAnswers();
			 centrY = yCoord / 3;
			 zCoord += tempList.get(i).getFilmNegativeAnswers();
			 centrZ = zCoord / 3;
		}
		HashMap<String, Double> centroid = new HashMap<String, Double>();
		centroid.put("xCoord", centrX);
		centroid.put("yCoord", centrY);
		centroid.put("zCoord", centrZ);
		return centroid;
	}
	
	public TreeMap<String, Double> euilerDistance(ArrayList<Film> filmList, HashMap<String, Double> centroid) {
		TreeMap<String, Double> resultMap = new TreeMap<String, Double>();
		double distance = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			distance = Math.pow((filmList.get(i).getFilmRaiting() - centroid.get("xCoord")), 2) +
					Math.pow((filmList.get(i).getFilmPositiveAnswers() - centroid.get("yCoord")), 2) + 
							Math.pow((filmList.get(i).getFilmNegativeAnswers() - centroid.get("zCoord")), 2);
			double result = Math.sqrt(distance);
			resultMap.put(filmList.get(i).getFilmTitle(), result);
		}
		return resultMap;
	}
	
	public TreeMap<String, Double> manhattanDistance(ArrayList<Film> filmList, HashMap<String, Double> centroid) {
		TreeMap<String, Double> resultMap = new TreeMap<String, Double>();
		double distance = 0.0;
		for (int i = 0; i < filmList.size(); i++) {
			double x = Math.abs(filmList.get(i).getFilmRaiting() - centroid.get("xCoord"));
			double y =  Math.abs(filmList.get(i).getFilmPositiveAnswers() - centroid.get("yCoord"));
			double z =  Math.abs(filmList.get(i).getFilmNegativeAnswers() - centroid.get("zCoord"));
			distance = x + y + z;
			resultMap.put(filmList.get(i).getFilmTitle(), distance);
		}
		return resultMap;
	}
}
