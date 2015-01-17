package Model.dto_beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import Model.others.ParamContainer;
import Model.others.PriceFilter;


public class ParamFilterBean implements Cloneable{
	private List<ParamContainer> params = new ArrayList<ParamContainer>();
	private List<PriceFilter> pricefilter = new ArrayList<PriceFilter>();
	private Integer catid;
	private Integer pagenum;
		
	private Set<ParamContainer> sortedparams = new TreeSet<ParamContainer>();

	public Set<ParamContainer> getSortedparams() {
		return sortedparams;
	}

	public void setSortedparams(Set<ParamContainer> sortedparams) {
		this.sortedparams = sortedparams;
	}
	
	public void fillsortedparams(){
		sortedparams = new TreeSet<ParamContainer>(params);
	}

	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	public Integer getCatid() {
		return catid;
	}

	public void setCatid(Integer catid) {
		this.catid = catid;
	}

	public List<PriceFilter> getPricefilter() {
		return pricefilter;
	}

	public void setPricefilter(List<PriceFilter> pricefilter) {
		this.pricefilter = pricefilter;
	}

	public List<ParamContainer> getParams() {
		return params;
	}

	public void setParams(List<ParamContainer> params) {
		this.params = params;
	}

	public ParamFilterBean copyParamVals(ParamFilterBean paramFilterBean) throws CloneNotSupportedException {
		int index=0;
		
		ParamFilterBean pfltb = paramFilterBean.clone();
		
		for(ParamContainer t: this.getParams()){
			for(ValueBean v: t.getVals()){
				for(ParamContainer c: pfltb.getParams()){
					if(c.getPropid().equals(t.getPropid())){
					index=c.getVals().indexOf(v);
					System.out.println("indexOf("+v.getId()+v.getValue()+") = " + index);
					if(index==-1){
						v.setIschecked(false);
						c.getVals().add(v);
						
					}
					}
				}
			}
		}
	
		return pfltb;
	}

	@Override
	public ParamFilterBean clone() throws CloneNotSupportedException {
		ParamFilterBean pfltb = new ParamFilterBean();
		List<ParamContainer> newparams = new ArrayList<ParamContainer>();
		for(ParamContainer pr: this.params)
		{
			newparams.add((ParamContainer)pr.clone());
		}
		
		List<PriceFilter> newpricefilters = new ArrayList<PriceFilter>();
		for(PriceFilter prc: this.pricefilter)
		{
			newpricefilters.add((PriceFilter)prc.clone());
		}
		pfltb.setCatid(new Integer(getCatid().intValue()));
		pfltb.setPagenum(new Integer(getPagenum().intValue()));
		pfltb.setParams(newparams);
		pfltb.setPricefilter(newpricefilters);
		return pfltb;
		

	}



	public ParamFilterBean() {

	}
	

}
