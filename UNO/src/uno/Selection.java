package uno;

public class Selection {
	private String selNumber;
	private String selText;
	Selection() {
		
	}
	
	Selection(String selNumber, String selText) {
		setSelNumber(selNumber);
		setSelText(selText);
	}
	
	void setSelNumber(String selNumber) {
		this.selNumber = selNumber;
	}
	
	String getSelNumber() {
		return selNumber;
	}
	
	void setSelText(String selText) {
		this.selText = selText;
	}
	
	String getSelText() {
		return selText;
	}
}
