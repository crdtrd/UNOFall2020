package uno;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreatePlayers {
	private static int numIPlayers;
	private static int numCPlayers;
	private static ArrayList<Player> players = new ArrayList<>();
	private static Scanner ui = new Scanner(System.in);
	
	public static void playPlayerCreate() throws InterruptedException, FileNotFoundException {
		players.clear();
		promptIPlayers();
		promptCPlayers();
		namePlayers();
	}
	
	public static void promptIPlayers() {
		System.out.println("How many intelligent players? "); 
		numIPlayers = ui.nextInt();
	}
	
	public static void promptCPlayers() throws FileNotFoundException {
		System.out.println("How many computer players? "); 
		numCPlayers = ui.nextInt();
		//behold... the list
		String[] names = {"Petr", "Ivan", "Yelisey", "Ryzhanov", "Sobolev", "Khristina", 
				"Sofya", "Gusarova", "Gustav", "Bukov", "Shigayev", "Lana", "Anton", "Aleksei", 
				"Gerogii", "Mariya", "Feofora", "Tanya", "Adrik", "Evgenii", "Matvei", "Jeremija", 
				"Bob", "Joe", "YOUR_MOM", "Denis", "Prof_J", "Dr_Phibes", "Agent_2","Shaggy_Rogers", 
				"pp", "PP", "YOUR_DAD", "shoe", "The_Underminer", "Zorro", "Luigi", "Mario", "Mr_Yoshi", 
				"John_Cena"};
		
		for(int p = 1; p <= numCPlayers; p++) {
			Player pooterPlayer = new Player();
			pooterPlayer.setPooterPlayer(true);
			pooterPlayer.setName(names[(int) (Math.random()*names.length-1)]);
			players.add(pooterPlayer);
			System.out.println(pooterPlayer.getName());
		}
	}
	
	public static void namePlayers() {
		System.out.println("\nName Your Players");
		for(int p = 1; p <= numIPlayers; p++) {
			System.out.println("Player " + p + ": ");
			Player player = new Player(ui.next());
			players.add(player); 
		} 
	}
	
	public static ArrayList<Player> getPlayers() {
		return players;
	}
}
