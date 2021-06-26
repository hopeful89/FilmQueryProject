package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class UserMenu {
	private DatabaseAccessor db = new DatabaseAccessorObject();
	private UserDisplay display = new UserDisplay();
	private VerifyInput inputVerify = new VerifyInput();
	public final static int MAINMENUOPTIONCOUNT = 3;
	public final static int SUBMENUOPTIONCOUNT = 2;
	
	public boolean startUserMainMenu(int userInput, Scanner input) {

		switch (userInput) {
		case 1:
			display.printSearchType("id");
			int lookupID = inputVerify.verifyUserSingleInput(input, Integer.MAX_VALUE);
			Film userFilm = db.findFilmById(lookupID);
			if( userFilm != null) {
				display.printFilmInformation(userFilm);
				startUserSubMenu(input, SUBMENUOPTIONCOUNT, userFilm);
				System.out.println();
			}else {
				display.printNoFilmFound(lookupID);
			}
			break;
		case 2:
			display.printSearchType("keyword");
			
			String userWord = input.nextLine();
			List<Film> keyWordFilms = db.findFilmByKeyword(userWord);
			Film[] keyWordFilmArray = new Film[keyWordFilms.size()];
			
			if (keyWordFilms.size() > 0) {
				keyWordFilms.forEach(film -> display.printFilmInformation(film));
				startUserSubMenu(input, SUBMENUOPTIONCOUNT, keyWordFilms.toArray(keyWordFilmArray));
			} else {
				display.printNoFilmFound(userWord);
			}
			break;
		case 3:
			return false;

	}
	return true;
}
	
	private void startUserSubMenu(Scanner input, int menuCount, Film... films) {
		display.printSubMenu();
		int userInput = inputVerify.verifyUserSingleInput(input, menuCount);
		
		switch (userInput) {
		case 1:
			for (Film film : films) {
				display.printFilmInformation(film.toString());
			}
			break;
		case 2: 
			break;
		}
	}
}
