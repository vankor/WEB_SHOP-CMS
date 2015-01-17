package Model.dto_beans;

import java.util.ArrayList;
import java.util.List;

public class MultiChangeBean {
	private String classname;
	private String[] ids;
	private List<MultiValueBean> propvals = new ArrayList<MultiValueBean>();
	private Integer pgnum;
	
	
	public Integer getPgnum() {
		return pgnum;
	}
	public void setPgnum(Integer pgnum) {
		this.pgnum = pgnum;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public List<MultiValueBean> getPropvals() {
		return propvals;
	}
	public void setPropvals(List<MultiValueBean> propvals) {
		this.propvals = propvals;
	}


}
