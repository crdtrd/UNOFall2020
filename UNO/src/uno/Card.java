package uno;

public class Card {
	private int value;
	private String color;
	private boolean isFirstCard;
	private boolean isWild;
	
	//noarg
	Card () {	
	}
	
	Card(int value, String color) {
		setColor(color);
		setValue(value);
	}
	
	String getColor() {
		return color;
	}
	
	int getValue() {
		return value;
	}
	
	void setColor(String color) {
		this.color = color;
	}
	
	void setValue(int value) {
		this.value = value;
	}
	
	void setFirstCard(boolean b) {
		isFirstCard = b;
	}
	
	void setWild() {
		isWild = true; color = ""; value = -1;
	}
	
	boolean isValue(int value) {
		if (this.value==value)
			return true;
		return false;
	}
	
	boolean isColor(String color) {
		if (this.color.equals(color)) 
			return true;
		return false;
	}
	
	boolean isFirstCard() {
		return isFirstCard;
	}
	
	boolean isWild() {
		return isWild;
	}
	
	String toCardString() {
		if(isWild()) {
			return String.format(color + "Wild");
		} else {
			return String.format(color + " " + value);
		}
		
	}
	
	
	//NOT USED
	//display a card
	void displayCard() {
		System.out.println(color + " " + value);
	}
}


