package uno;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> hand = new ArrayList<>();
	private boolean isPooterPlayer;
	
	//noarg
	Player() {
	}
	//name constructor
	Player(String name) {
		setName(name);
	}
	//setname
	void setName(String name) {
		this.name = name;
	}
	
	void setPooterPlayer (boolean b) {
		isPooterPlayer = b;
	}
	
	boolean isPooterPlayer() {
		return isPooterPlayer;
	}
	//getName
	String getName() {
		return name;
	}
	
	//add a card from somewhere
	void receiveCard(Card card) {
		hand.add(card);
	}
	//get a specific card object
	Card getCard(int index) {
		return hand.get(index);
	}
	//get number of cards in hand
	int getHandSize() {
		return hand.size();
	}
	//remove a card from hand
	void removeCard(int index) {
		hand.remove(index);
	}
	//send a card to a deck
	void playCard(int index, Deck deck) {
		deck.recieveCard(hand.get(index));
	}
	
	//stringitize the whole hand for debugging
	void displayHand() {
		for (int i = 0; i < hand.size(); i++) 
			hand.get(i).displayCard();
	}
	
	ArrayList<Card> getHand() {
		return hand;
	}
}
