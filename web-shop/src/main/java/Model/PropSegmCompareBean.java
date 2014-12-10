package Model;

import java.util.Set;
import java.util.TreeSet;

public class PropSegmCompareBean implements Comparable{
	private PropSegment sgm;
	private Set<PropertyCompareBean> propbeans = new TreeSet<PropertyCompareBean>();

	
	public PropSegment getSgm() {
		return sgm;
	}

	public void setSgm(PropSegment sgm) {
		this.sgm = sgm;
	}

	public Set<PropertyCompareBean> getPropbeans() {
		return propbeans;
	}

	public void setPropbeans(Set<PropertyCompareBean> propbeans) {
		this.propbeans = propbeans;
	}
	
	@Override
	public int compareTo(Object arg0) {
		PropSegmCompareBean othergood = null;
		if(arg0 instanceof PropSegmCompareBean){	
			othergood = (PropSegmCompareBean)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.sgm.getName().compareTo(othergood.sgm.getName());
	}
	
	
}
