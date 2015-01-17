package Model.others;

public class ChoiseItem {

	private String label;
	public ChoiseItem(String label, String value) {
	
		this.label = label;
		this.value = value;
	}






	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private String value;
}
