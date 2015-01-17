package Model.others;

import Model.dto_beans.ParamFilterBean;
import Model.dto_beans.ValueBean;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class ParamContainer implements Cloneable, Comparable{
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ParamContainer prcntr = new ParamContainer();
		List<ValueBean> newvals = new ArrayList<ValueBean>();
		for(ValueBean valbean: vals){
			newvals.add((ValueBean)valbean.clone());
		}
		prcntr.setPropid(new Integer(propid));
		prcntr.setPropname(propname);
		prcntr.setVals(newvals);
		
		return prcntr;
	}


	private Integer propid;
	private String propname;

	private List<ValueBean> vals = new ArrayList<ValueBean>();
	private Integer valcount;
	private Integer priority;
	
	
	public Integer getMainvalscount() {
		return mainvalscount;
	}

	public void setMainvalscount(Integer mainvalscount) {
		this.mainvalscount = mainvalscount;
	}


	private Integer mainvalscount;
	
	@NotNull
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@NotNull
	public Integer getValcount() {
		return vals.size();
	}

	public void setValcount(Integer valcount) {
		this.valcount = valcount;
	}

	public void copyPropValues(ParamFilterBean propfilter){
		int index=0;
		for(ParamContainer t: propfilter.getParams()){
			if(this.propid == t.getPropid()){
				for(ValueBean v: t.getVals()){
					if((index=vals.indexOf(v))>-1){
						System.out.println(vals.get(index).getId()+"  "+vals.get(index).getValue());
						vals.set(index, v);
					}
					
				}
			}
		}
	}
	
	public List<ValueBean> getVals() {
		return vals;
	}
	public void setVals(List<ValueBean> vals) {
		this.vals = vals;
	}
	public Integer getPropid() {
		return propid;
	}
	public void setPropid(Integer propid) {
		this.propid = propid;
	}
	public String getPropname() {
		return propname;
	}
	public void setPropname(String propname) {
		this.propname = propname;
	}


	
	public void addParam(ValueBean val) {
		vals.add(val);
	}

	@Override
	public int compareTo(Object arg0) {
		ParamContainer othergood = null;
		if(arg0 instanceof ParamContainer){	
			othergood = (ParamContainer)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.priority.compareTo(othergood.priority);
	}
	
}
