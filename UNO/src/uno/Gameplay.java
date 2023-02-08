package uno;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Gameplay {
	private static ArrayList<Player> players = CreatePlayers.getPlayers();
	private static Deck deck;
	private static int playerIndex = (int) (players.size()*Math.random());
	private static Player currentPlayer = players.get(playerIndex);
	
	//noarg
	Gameplay() {
	}
	
	//dealing phase
	static void deal() throws InterruptedException, FileNotFoundException {
		System.out.println(currentPlayer.getName() + " is dealing!");
		if (deck != null)
		{
			deck.getCards().clear();
		}
		deck = new Deck(players.size() / 11 + 1);
		deck.shufDeck();
		for (Player p: players) {
			p.getHand().clear();
			deck.dealCard(7, p);
		}
		Thread.sleep(1000);
		deck.getCard(0).setFirstCard(true);
		System.out.println("First card is " + deck.getCard(0).toCardString());
		nextTurn();
	}
	
	//advance to next player
	static void nextTurn() throws InterruptedException, FileNotFoundException {
		if (playerIndex < players.size()-1) {
			playerIndex++;
			currentPlayer = players.get(playerIndex);
		}
		else {
			playerIndex=0;
			currentPlayer = players.get(playerIndex);
		}
		
		System.out.println("\nIt is " + currentPlayer.getName() + "'s turn:");
		Thread.sleep(1000);
		if (currentPlayer.isPooterPlayer()) {
			//do pooter player things
			//cycle through cards for color
			for (int index = 0; index < currentPlayer.getHandSize(); index++) {
				Card card = currentPlayer.getCard(index);
				if (card.isColor(deck.getCard(0).getColor())) {
					PlayerMenu.pooterPlayedCard(currentPlayer, deck, index);
					nextTurn();
				} 
			}
			//cycle though cards for wild
			for (int index = 0; index < currentPlayer.getHandSize(); index++) {
				Card card = currentPlayer.getCard(index);
				if (card.isWild()) {
					PlayerMenu.pooterPlayedCard(currentPlayer, deck, index);
					pooterWild(deck.getCard(0));
					nextTurn();
				} 
			}
			//cycle through cards for value
			for (int index = 0; index < currentPlayer.getHandSize(); index++) {
				Card card = currentPlayer.getCard(index);
				if (card.isValue(deck.getCard(0).getValue())) {
					PlayerMenu.pooterPlayedCard(currentPlayer, deck, index);
					nextTurn();
				} 
			}
			//if first card is special
			if (deck.getCard(0).isWild() && deck.getCard(0).isFirstCard())
			{
				PlayerMenu.pooterPlayedCard(currentPlayer, deck, 0); 
				nextTurn();
			}
			//draw counter 
			int drawCounter = 0;
			//if none of those, draw a card and check it
			while (!currentPlayer.getCard(currentPlayer.getHandSize()-1).isColor(deck.getCard(0).getColor())
					&& !currentPlayer.getCard(currentPlayer.getHandSize()-1).isValue(deck.getCard(0).getValue())
					&& !currentPlayer.getCard(currentPlayer.getHandSize()-1).isWild()) 
			{
				if (deck.getSize() < 2) {
					System.out.println("No more cards to draw! " + currentPlayer + " skips their turn!");
					Thread.sleep(1500);
					nextTurn();
				}
				//should shuffle discard pile?
				else if (deck.getCard(deck.getSize()-1).isFirstCard()) {
					//save topCard and shuffle
					System.out.println("Reshuffling discard pile!");
					Thread.sleep(1500);
					deck.getCard(deck.getSize()-1).setFirstCard(false);
					Card topCard = deck.getCard(0); topCard.setFirstCard(true);
					deck.removeCard(0);
					deck.shufDeck();
					deck.recieveCard(topCard);
				} else deck.dealCard(1, currentPlayer); drawCounter++;
			}
				
			
			//if wild, pick color 
			if (currentPlayer.getCard(currentPlayer.getHandSize()-1).isWild()) {
				System.out.println(currentPlayer.getName() + " drew " + drawCounter + " cards.");
				Thread.sleep(1500);
				PlayerMenu.pooterPlayedCard(currentPlayer, deck, currentPlayer.getHandSize()-1);
				pooterWild(deck.getCard(0));
				nextTurn();
			} else 
				System.out.println(currentPlayer.getName() + " drew " + drawCounter + " cards.");
				Thread.sleep(1500);
				PlayerMenu.pooterPlayedCard(currentPlayer, deck, currentPlayer.getHandSize()-1);
				nextTurn();
		} else {
			PlayerMenu.accessMenu(currentPlayer, deck);
		}
	}
	
	//check if someone won
	static void winCheck(Player p) throws InterruptedException, FileNotFoundException {
		if (p.getHandSize() == 0) {
			Thread.sleep(1500);
			System.out.println(p.getName() + " wins!\n");
			MainMenu.playMainMenu();
		} else
			return;
	}
	
	//advance 50 lines for dramatic effect, got off StackOverflow
	static void clearConsole() {
		System.out.println(new String(new char[50]).replace("\0", "\r\n"));
	}
	
	static void pooterWild(Card wild) throws InterruptedException {
		String[] colors = new String[] {"Red", "Green", "Blue", "Yellow"};
		wild.setColor(colors[(int) (Math.random()*4)]);
		System.out.println("The new color is " + wild.getColor() + "!");
		Thread.sleep(1500);
	}
}
