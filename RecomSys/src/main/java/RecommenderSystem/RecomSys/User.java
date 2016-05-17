package RecommenderSystem.RecomSys;

import java.util.ArrayList;

public class User {
	
	private int userId;
	private String userName;
	private String userPassword;
	private ArrayList<Film> favouritesFilms;
	
	
	User(int aUserId, String aUserName, String aUserPass) {
		userId = aUserId;
		userName = aUserName;
		userPassword = aUserPass;
		favouritesFilms = new ArrayList<Film>();
	}


	public int getUserId() {
		return userId;
	}



	public String getUserName() {
		return userName;
	}



	public String getUserPassword() {
		return userPassword;
	}


	public ArrayList<Film> getFavouritesFilms() {
		return favouritesFilms;
	}


	public void setFavouritesFilms(ArrayList<Film> favouritesFilms) {
		this.favouritesFilms = favouritesFilms;
	}


}
