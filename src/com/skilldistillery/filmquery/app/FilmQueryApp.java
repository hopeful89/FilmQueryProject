package com.skilldistillery.filmquery.app;

import java.util.Scanner;

public class FilmQueryApp {

	private UserDisplay display = new UserDisplay();
	private VerifyInput inputVerify = new VerifyInput(); 
	private UserMenu menu = new UserMenu();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
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
			display.printMainMenuSelection();
			int userInput = inputVerify.verifyUserSingleInput(input, UserMenu.MAINMENUOPTIONCOUNT);
			stillChoosing = menu.startUserMainMenu(userInput, input);
		}
		display.printEndingStatement();
	}
}
