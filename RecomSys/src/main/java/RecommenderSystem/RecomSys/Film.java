package RecommenderSystem.RecomSys;

public class Film {
	
	private int filmId;
	private String filmTitle;
	private String filmGenre;
	private int filmYearOfCreation;
	private double filmRaiting;
	private double filmPositiveAnswers;
	private double filmNegativeAnswers;
	
	
	Film(int anId, String aFilmTitle, String aFilmGenre, int aFilmYearOfCreation, 
			double aFilmRaiting, double aFilmPositiveAnswers, double aFilmNegativeAnswers) {
		filmTitle = aFilmTitle;
		filmGenre = aFilmGenre;
		filmYearOfCreation = aFilmYearOfCreation;
		filmRaiting = aFilmRaiting;
		filmPositiveAnswers = aFilmPositiveAnswers;
		filmNegativeAnswers = aFilmNegativeAnswers;
	}


	public String getFilmTitle() {
		return filmTitle;
	}


	public String getFilmGenre() {
		return filmGenre;
	}


	public int getFilmYearOfCreation() {
		return filmYearOfCreation;
	}


	public double getFilmRaiting() {
		return filmRaiting;
	}


	public double getFilmPositiveAnswers() {
		return filmPositiveAnswers;
	}


	public double getFilmNegativeAnswers() {
		return filmNegativeAnswers;
	}


	public int getFilmId() {
		return filmId;
	}


	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}


	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
	}


	public void setFilmGenre(String filmGenre) {
		this.filmGenre = filmGenre;
	}


	public void setFilmYearOfCreation(int filmYearOfCreation) {
		this.filmYearOfCreation = filmYearOfCreation;
	}


	public void setFilmRaiting(double filmRaiting) {
		this.filmRaiting = filmRaiting;
	}


	public void setFilmPositiveAnswers(double filmPositiveAnswers) {
		this.filmPositiveAnswers = filmPositiveAnswers;
	}


	public void setFilmNegativeAnswers(double filmNegativeAnswers) {
		this.filmNegativeAnswers = filmNegativeAnswers;
	}

	
}
