package Model.dto_beans;

import java.util.ArrayList;
import java.util.List;

public class FilterNodeBean {
	private String adminclassname;
	private String propname;
	private List<ValueBean> vals = new ArrayList<ValueBean>();
	private String min;
	private String max;
	
	private Integer pgnum;
	private String classname;
	private String propclassname;
	private String coladmintype;
	
	private Boolean issortcriteria;
	private String colname;
	private String currurl;
	
	private Boolean isobject;
	private Boolean hasvaluefilter; 
	private Boolean hasrangefilter; 
	
	public Boolean getHasrangefilter() {
		return hasrangefilter;
	}

	public void setHasrangefilter(Boolean hasrangefilter) {
		this.hasrangefilter = hasrangefilter;
	}

	public Boolean getHasvaluefilter() {
		return hasvaluefilter;
	}

	public void setHasvaluefilter(Boolean hasvaluefilter) {
		this.hasvaluefilter = hasvaluefilter;
	}

	public String getPropclassname() {
		return propclassname;
	}

	public void setPropclassname(String propclassname) {
		this.propclassname = propclassname;
	}
	
	public Boolean getIsobject() {
		return isobject;
	}

	public void setIsobject(Boolean isobject) {
		this.isobject = isobject;
	}

	public String getColadmintype() {
		return coladmintype;
	}

	public void setColadmintype(String coladmintype) {
		this.coladmintype = coladmintype;
	}
	
	public String getCurrurl() {
		return currurl;
	}

	public void setCurrurl(String currurl) {
		this.currurl = currurl;
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}
	
	
	public Boolean getIssortcriteria() {
		return issortcriteria;
	}

	public void setIssortcriteria(Boolean issortcriteria) {
		this.issortcriteria = issortcriteria;
	}
	
	public String getAdminclassname() {
		return adminclassname;
	}

	public void setAdminclassname(String adminclassname) {
		this.adminclassname = adminclassname;
	}

	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public Integer getPgnum() {
		return pgnum;
	}
	public void setPgnum(Integer pgnum) {
		this.pgnum = pgnum;
	}
	public String getPropname() {
		return propname;
	}
	public void setPropname(String propname) {
		this.propname = propname;
	}
	
	public List<ValueBean> getVals() {
		return vals;
	}
	public void setVals(List<ValueBean> values) {
		this.vals = values;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	
	

}
