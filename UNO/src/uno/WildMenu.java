package uno;

import java.util.Scanner;

public class WildMenu {
	private static Menu wildMenu = new Menu();
	static Scanner ui = new Scanner(System.in);
	private static String[] colors = new String[] {"Red", "Green", "Blue", "Yellow"};
	
	//noarg
	WildMenu() {
	}
	
	static void playWildMenu (Card wild) throws InterruptedException {
		wildMenu.addSelection("1", colors[0]);
		wildMenu.addSelection("2", colors[1]);
		wildMenu.addSelection("3", colors[2]);
		wildMenu.addSelection("4", colors[3]);
		System.out.println("The new color shall be: ");
		wildMenu.printMenu();
		wildMenu.clearMenu();
		
		String s = ui.next();
		switch (s) {
			case "1": {
				wild.setColor(colors[0]);
				break;
			}
			case "2": {
				wild.setColor(colors[1]);
				break;
			}
			case "3": {
				wild.setColor(colors[2]);
				break;
			}
			case "4": {
				wild.setColor(colors[3]);
				break;
			}
			default: {
				System.out.println("Input Invalid!");
				Thread.sleep(1000);
				playWildMenu(wild);
			}
		}
		System.out.println("The new color is " + wild.getColor() + "!");
		Thread.sleep(1500);
	}
}
