package Model;

public class ChoiseItem {
//	private Integer id;
	private String label;
	public ChoiseItem(String label, String value) {
	
		this.label = label;
		this.value = value;
	}
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
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
