package uno;

import java.util.ArrayList;

public class Menu {
	private ArrayList<Selection> menu = new ArrayList<>();
	
	//noarg
	Menu() {
	}
	
	//add a selection number and text to the menu
	void addSelection(String num, String text) {
		Selection selection = new Selection(num, text);
		menu.add(selection);
	}
	
	//get the selection number as a string
	String getSelectionNumber(int index) {
		return menu.get(index).getSelNumber();
	}
	
	//print the menu to console
	void printMenu() {
		for(Selection i: menu) {
			System.out.printf("[%s] %s%n", i.getSelNumber(), i.getSelText());
		}
	}
	
	//get number of selections in menu
	int getMenuSize() {
		return menu.size();
	}
	
	//clear the menu of any selections.
	void clearMenu() {
		menu.clear();
	}
	
	//check if passed selectionNumber matches any selections. Not currently using
	//could return the selection it lands on as opposed to a boolean. May make selecting
	//cards more streamlined.
	boolean checkSelectionNumber(String selectionNumber) {
		for (int i = 0; i < menu.size(); i++)
			if (selectionNumber.equals(menu.get(i).getSelNumber())) {
				return true;
			}
		return false;
	}
}
