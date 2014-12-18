package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Query;


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
		
	/*	Object obj = null;
        try {
            
            FastByteArrayOutputStream fbos = 
                    new FastByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(fbos);
            out.writeObject(this);
            out.flush();
            out.close();

         
            ObjectInputStream in = 
                new ObjectInputStream(fbos.getInputStream());
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return (ParamFilterBean)obj;*/
	}



	public ParamFilterBean() {
		// TODO Auto-generated constructor stub
	}
	

}
