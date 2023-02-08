package uno;

import java.io.FileNotFoundException;

public class Driver {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		//Gameplay.clearConsole();
		System.out.println("Welcome to UNO!");
		Thread.sleep(1500);
		
		CreatePlayers.playPlayerCreate();
	    MainMenu.playMainMenu();
	    
	}
}
