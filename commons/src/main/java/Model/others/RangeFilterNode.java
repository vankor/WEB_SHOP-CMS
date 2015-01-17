package Model.others;

import java.util.List;


public class RangeFilterNode extends FilterNode implements Comparable {
	

	{type = FilterType.RANGE;}

	public RangeFilterNode(List<Object> values) {
		if(values.size()>2)throw new IllegalArgumentException("Должно быть два элемента - минимальный и максимальный");
		this.values = values;

	}

	@Override
	public FilterType getType() {
		return type;
	}

	@Override
	public List<Object> getValues() {

		return values;
	}
	
	@Override
	public void addValue(Object g) {}

	@Override
	public void setValues(List<Object> g) {
		if(values.size()>2)throw new IllegalArgumentException("Должно быть два элемента - минимальный и максимальный");
		this.values = values;
		
	}

	@Override
	public int compareTo(Object arg0) {

		return hashCode()-arg0.hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		RangeFilterNode other = (RangeFilterNode) obj;
		if (type != other.type)
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}




}
