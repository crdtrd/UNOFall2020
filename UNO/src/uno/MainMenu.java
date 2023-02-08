package uno;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu {
	private static Menu mainMenu = new Menu();
	static Scanner ui = new Scanner(System.in);
	
	//noarg
	MainMenu() {
	}
	
	//the main menu
	static void playMainMenu() throws InterruptedException, FileNotFoundException {
		mainMenu.addSelection("1", "Start");
		mainMenu.addSelection("2", "Players");
		mainMenu.addSelection("0", "Quit");
		mainMenu.printMenu();
		mainMenu.clearMenu();
		
		String s = ui.next();
		if (s.equals("1")) {
			//start
			Gameplay.clearConsole();
			Gameplay.deal();
		} else if (s.equals("2")) {
			//players
			Gameplay.clearConsole();
			CreatePlayers.playPlayerCreate();
			playMainMenu();
		} else if (s.equals("0")) {
			//quit game
			Gameplay.clearConsole();
			System.out.println("later.");
			System.exit(0);
		} else {
			System.out.println("Input Invalid!");
			Thread.sleep(1000);
			playMainMenu();
		}
	}
}
