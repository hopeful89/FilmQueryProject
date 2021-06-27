package com.skilldistillery.filmquery.app;

import com.skilldistillery.filmquery.entities.Film;

public class UserDisplay {
	
	public void printMainMenuSelection() {
		System.out.println("***************************************");
		System.out.println();
		System.out.println("1) Look up a film by its id.");
		System.out.println("2) Look up a film by a search keyword.");
		System.out.println("3) Exit the application.");
		System.out.println("_______________________________________");
		System.out.println("_______________________________________");
		System.out.println();
		System.out.print("       What would you like to do? ");
	}
	
	public void printFilmInformation(Film film) {
		System.out.println("TITLE: " + film.getTitle() + "\n" + "DESCRIPTION: " + film.getDescription() + "\n"
				+ "Language: " + film.getLanguage() + "\n" + "Year: " + film.getReleaseYear() + "\n" + "Rating: "
				+ film.getRating() + "\n" + "Category: " + film.getCategory());
		System.out.print("Actors: ");
		film.getFeaturedActors().forEach(actor -> System.out.print(actor + ", "));
		System.out.println("\n");
	}
	
	public void printFilmInformation(String filmInfo) {
		System.out.println();
		System.out.println(filmInfo);
	}
	
	public void printSubMenu() {
		System.out.println("1) View full film details.");
		System.out.println("2) Return to main menu.");
		System.out.println("---------------------------");
		System.out.print("What would you like to do? ");
	}
	
	public void printSearchType(String type) {
		if(type.equals("keyword")) {
			System.out.println("What keyword to be used to search films?");
			System.out.println();
		}else {
			System.out.print("What film id to look up? ");
			System.out.println();
		}
	}
	
	public void printNoFilmFound(int filmId) {
		System.out.println("No Film found for id: " + filmId);
	}
	
	public void printNoFilmFound(String userWord) {
		System.out.println("No Film found for keyword: " + userWord);
	}
	
	public void printMenuPossibleOptions(int count) {
		System.out.println("Enter number between 1 and " + count);
	}
	
	public void printInvalidEntry() {
		System.out.println("Invalid entry");
	}
	
	public void printEndingStatement() {
		System.out.println("Thank you! Please Come again.");
	}
	
}
