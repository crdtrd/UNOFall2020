package uno;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<>();
	private String[] colors = new String[] {"Red", "Green", "Blue", "Yellow"};
	
	//noarg
	Deck () {
	}
	//number of decks constructor
	Deck (int decks) {
		createDeck(decks);
	}
	
	//construct a number of decks
	void createDeck (int decks) {
		for(int d = 0; d < decks; d++) 
		{
			int index = 0;
			for(int k = 0; k < 2; k++) 
			{
				for (int value = 9; value > -1; value--) 
				{
					 for (int color = 0; color < colors.length; color++)
					 {
						if (index < 76) 
						{
							Card card = new Card(value, colors[color]);
							deck.add(index, card);
							index++;
						}
					 }
				}
			}
			for (int w = 0; w < 4; w++) {
				Card wild = new Card();
				wild.setWild(); deck.add(wild);
			}
		}
	}
	
	//get the array of cards that is the deck
	ArrayList<Card> getCards() {
		return deck;
	}
	
	//shuffle
	void shufDeck() {
		java.util.Collections.shuffle(deck);
	}
	
	//display the deck of cards
	void displayDeck () {
		for (int i = 0; i < deck.size(); i++) {
			deck.get(i).displayCard();
		}
		System.out.println("Deck size is " + deck.size());
	}
	
	//deal a number of cards to a hand
	void dealCard (int numberOfCards, Player p) {
		for(int i = 1; i <= numberOfCards; i++) 
		{
			p.receiveCard(deck.get(deck.size()-i));
			deck.remove(deck.size()-i);
		}
	}
	//get a specific card in deck
	Card getCard(int index) {
		return deck.get(index);
	}
	//recieve a card from somewhere
	void recieveCard(Card card) {
		deck.add(0, card);
	}
	//get number of cards in deck
	int getSize() {
		return deck.size();
	}
	public void printTopCard() {
		System.out.println(deck.get(0).toCardString());
	}
	public void removeCard(int index) {
		deck.remove(index);
	}
	public boolean hasCards() {
		return !deck.isEmpty();
	}
}

