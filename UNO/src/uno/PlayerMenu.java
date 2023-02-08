package uno;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayerMenu {
	private static Menu playerMenu = new Menu();
	static Scanner ui = new Scanner(System.in);
	private static int drawCounter = 0;
	
	//noarg
	PlayerMenu() {
	}
	
	//the menu asking to show the player's cards
	static void accessMenu(Player p, Deck deck) throws InterruptedException, FileNotFoundException {
		if(playerMenu.getMenuSize() < 1) {
			playerMenu.addSelection("0", "Show Cards");
		}
		playerMenu.printMenu();
		playerMenu.clearMenu();
		
		String s = ui.next();
		switch (s) {
			case "0": {
				Gameplay.clearConsole(); cardMenu(p, deck);
			}
			default: {
				System.out.println("Input Invalid!");
				Thread.sleep(1000);
				accessMenu(p, deck);
			}
		}
	}
	
	//the card selection menu. She's kind of a monster
	static void cardMenu(Player p, Deck deck) throws InterruptedException, FileNotFoundException {
		
		//add the cards to menu
		for(int i = 0; i < p.getHandSize(); i++) {
			String card = p.getCard(i).toCardString();
			String selNum = Integer.toString(i+1);
			playerMenu.addSelection(selNum, card);
		}
		
		//add the alternative options
		playerMenu.addSelection("-", "Draw Card");
		playerMenu.addSelection("0", "Hide Cards");
		
		//print menu get input
		deck.printTopCard(); System.out.println(); 
		playerMenu.printMenu();
		playerMenu.clearMenu();
		String playerSelection = ui.next();
		try
		{
			//switch statement instead
			switch (playerSelection) {
				case "0": { //hide card
					Gameplay.clearConsole();
					accessMenu(p, deck);
				}
				case "-": { //draw card
					//out of cards to draw?
					if (deck.getSize() < 2) {
						System.out.println("No more cards to draw!");
						Thread.sleep(1000);
						outOfCards(p, deck);
					}
					//should shuffle discard pile?
					else if (deck.getCard(deck.getSize()-1).isFirstCard()) {
						//save topCard and shuffle
						System.out.println("Reshuffling discard pile!");
						Thread.sleep(1000);
						deck.getCard(deck.getSize()-1).setFirstCard(false);
						Card topCard = deck.getCard(0); topCard.setFirstCard(true);
						deck.removeCard(0);
						deck.shufDeck();
						deck.recieveCard(topCard);
					}
					deck.dealCard(1, p); 
					drawCounter++;
					Gameplay.clearConsole(); playerMenu.clearMenu(); cardMenu(p, deck);
				}
				default: { //play card or invalid
					int selectedCardIndex = Integer.parseInt(playerSelection)-1;
					if (p.getCard(selectedCardIndex).isWild()) {
						playedCard(p, deck, selectedCardIndex); 
						WildMenu.playWildMenu(deck.getCard(0));
						Gameplay.nextTurn();
					}
					else if(p.getCard(selectedCardIndex).isValue(deck.getCard(0).getValue())||
							p.getCard(selectedCardIndex).isColor(deck.getCard(0).getColor())
							//fix for wild first card
							|| (deck.getCard(0).isWild() && deck.getCard(0).isFirstCard())) 
					{
						playedCard(p, deck, selectedCardIndex); Gameplay.nextTurn();
					} 
					
					else {
						System.out.println("Input Invalid!");
						playerMenu.clearMenu();
						Thread.sleep(1000);
						Gameplay.clearConsole();
						cardMenu(p, deck);
					}
				}
			}
		} catch (NumberFormatException num) {
			System.out.println("Input Invalid!");
			playerMenu.clearMenu();
			Thread.sleep(1000);
			Gameplay.clearConsole();
			cardMenu(p, deck);
		}
	}
	
	static void playedCard(Player p, Deck deck, int selectedCardIndex) throws InterruptedException, FileNotFoundException {
		Gameplay.clearConsole();
		p.playCard(selectedCardIndex, deck); p.removeCard(selectedCardIndex);
		playerMenu.clearMenu(); 
		if (drawCounter > 0) {
			System.out.println(p.getName() + " drew " + drawCounter + " cards.");
			Thread.sleep(1500);
		}
		if (deck.getCard(0).isWild()) {
			deck.getCard(0).setColor(null);
			deck.getCard(0).setColor("");
		}
		System.out.println(p.getName() + " played " + deck.getCard(0).toCardString() + "!");
		Gameplay.winCheck(p); 
		drawCounter = 0;
		Thread.sleep(1000);
	}
	
	static void pooterPlayedCard(Player p, Deck deck, int selectedCardIndex) throws InterruptedException, FileNotFoundException {
		p.playCard(selectedCardIndex, deck); p.removeCard(selectedCardIndex); 
		if (deck.getCard(0).isWild()) {
			deck.getCard(0).setColor(null);
			deck.getCard(0).setColor("");
		}
		System.out.println(p.getName() + " played " + deck.getCard(0).toCardString() + "!");
		Gameplay.winCheck(p); 
		Thread.sleep(1000);
	}
	
	static void outOfCards(Player p, Deck deck) throws InterruptedException, FileNotFoundException {
		Gameplay.clearConsole();
		Thread.sleep(1000);
		System.out.println("Skip Turn?");
		playerMenu.addSelection("1", "Yes");
		playerMenu.addSelection("0", "No");
		playerMenu.printMenu();
		playerMenu.clearMenu();
		String s = ui.next();
		switch (s) {
			case "1": {
				Gameplay.clearConsole();
				if (drawCounter > 0) {
					System.out.println(p.getName() + " drew " + drawCounter + " cards and skipped their turn.");
					Thread.sleep(1500);
				}
				drawCounter = 0;
				Gameplay.nextTurn();
			}
			case "0": {
				Gameplay.clearConsole();
				cardMenu(p, deck);
			}
			default: {
				System.out.println("Input Invalid!");
				playerMenu.clearMenu();
				Thread.sleep(1000);
				outOfCards(p, deck);
			}
		}
	}
}
