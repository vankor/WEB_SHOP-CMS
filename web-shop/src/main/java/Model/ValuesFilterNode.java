package Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ClassUtils;

public class ValuesFilterNode extends  FilterNode implements Comparable{
	
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
		ValuesFilterNode other = (ValuesFilterNode) obj;
		if (type != other.type)
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	{type = FilterType.VALUES;}
	
	
	

	public ValuesFilterNode(List<Object> values) {
		this.values = values;
	}

	@Override
	public FilterType getType() {
		return type;
	}

	@Override
	public List<Object> getValues() {
		// TODO Auto-generated method stub
		return values;
	}

	@Override
	public void addValue(Object g) {
		values.add(g);
		
	}

	@Override
	public void setValues(List<Object> g) {
		this.values = values;
		
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return hashCode()-arg0.hashCode();
	}

	


}
