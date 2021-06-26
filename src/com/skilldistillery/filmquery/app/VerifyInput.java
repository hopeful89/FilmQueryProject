package com.skilldistillery.filmquery.app;

import java.util.Scanner;

public class VerifyInput {
	
	public int verifyUserSingleInput(Scanner input, int count) {
		UserDisplay display = new UserDisplay();
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
					display.printMenuPossibleOptions(count);
				}
			} catch (NumberFormatException e) {
				display.printInvalidEntry();
				continue;
			}
		}
		return userInput;
	}
}
