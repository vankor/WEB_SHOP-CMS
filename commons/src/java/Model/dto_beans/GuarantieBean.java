package Model.dto_beans;

import Model.entity.Guarantie;

public class GuarantieBean {
	private Integer id;
	private String periodtype;
	private Integer count;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPeriodtype() {
		return periodtype;
	}
	public void setPeriodtype(String periodtype) {
		this.periodtype = periodtype;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public GuarantieBean(Guarantie guar){
		this.id = guar.getId();
		this.periodtype = guar.getPeriodtype();
		this.count = guar.getCount();
	}
	public GuarantieBean() {
		// TODO Auto-generated constructor stub
	}
}
