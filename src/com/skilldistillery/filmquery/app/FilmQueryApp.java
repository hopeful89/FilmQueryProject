package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	private DatabaseAccessor db = new DatabaseAccessorObject();
	private int menuOptionCount = 3;

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean stillChoosing = true;
		while(stillChoosing) {
			
			
			System.out.println("1) Look up a film by its id.");
			System.out.println("2) Look up a film by a search keyword.");
			System.out.println("3) Exit the application.");
			System.out.print("What would you like to do? ");
			
			int userInput = verifyUserInputInt(input, menuOptionCount);
			startUserChoice(userInput, input);
		}

	}

	private int verifyUserInputInt(Scanner input, int count) {
		int userInput = 0;
		boolean validInput = false;
		while (validInput != true) {
			try {
				userInput = Integer.parseInt(input.nextLine());
				if (userInput > 0 && userInput <= count) {
					validInput = true;
					return userInput;
				} else {
					userInput = 0;
					System.out.println("Enter number between 1 and " + count);
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid entry");
				continue;
			}
		}
		return userInput;
	}

	private void printFilmInformation(Film film) {
		System.out.println("TITLE: " + film.getTitle() + "\n" + "DESCRIPTION: " + film.getDescription() + "\n"
				+ "Language: " + film.getLanguage() + "\n" + "Year: " + film.getReleaseYear() + "\n" + "Rating: "
				+ film.getRating());
		System.out.print("Actors: ");
		film.getFeaturedActors().forEach(actor -> System.out.print(actor + ", "));
		System.out.println("\n");
	}

	private boolean startUserChoice(int userInput, Scanner input) {

			switch (userInput) {
			case 1:
				System.out.print("What film id to look up? ");
				int lookupID = verifyUserInputInt(input, Integer.MAX_VALUE);
				Film userFilm = db.findFilmById(lookupID);
				if( userFilm != null) {
					printFilmInformation(userFilm);
				}else {
					System.out.println("No Film found for id: " + lookupID);
				}
				break;
			case 2:
				System.out.print("What keyword to be used to search films?");
				String userWord = input.nextLine();
				List<Film> keyWordFilms = db.findFilmByKeyword(userWord);
				
				if (keyWordFilms.size() > 0) {
					keyWordFilms.forEach(film -> printFilmInformation(film));
				} else {
					System.out.println("No films found for " + userWord);
				}
				break;
			case 3:
				return false;
	
		}
		return true;
	}

}
