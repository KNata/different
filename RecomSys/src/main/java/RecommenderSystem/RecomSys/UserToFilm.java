package RecommenderSystem.RecomSys;

import java.util.ArrayList;

public class UserToFilm {
	
	private String userName;
	private ArrayList<Film> userChoice;
	
	UserToFilm(String aUserName, ArrayList<Film> anUserChoice) {
		userName = aUserName;
		
		userChoice = anUserChoice;
	}

	public String getUserName() {
		return userName;
	}

	public ArrayList<Film> getUserChoice() {
		return userChoice;
	}

	
}
