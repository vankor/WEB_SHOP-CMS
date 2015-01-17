package Model.dto_beans;

import Model.entity.Value;

public class ValueBean implements Cloneable {
	private Integer id;
	private String value;
	private Boolean ischecked = true;
	private Long count;
	

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Boolean getIschecked() {
		return ischecked;
	}

	public void setIschecked(Boolean ischecked) {
		this.ischecked = ischecked;
	}

	public ValueBean() {
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueBean other = (ValueBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ValueBean(Value val) {
		this.id=val.getId();
		this.value=val.getValue();
	}
	
	public ValueBean(Integer id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
