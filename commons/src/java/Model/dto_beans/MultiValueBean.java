package Model.dto_beans;

import java.lang.reflect.Type;

public class MultiValueBean {
	private String colname;
	private String classname;
	private String adminclassname;
	private Type type;
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	private Integer objid;
	
	public Integer getObjid() {
		return objid;
	}
	public void setObjid(Integer objid) {
		this.objid = objid;
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
	public String getColname() {
		return colname;
	}
	public void setColname(String colname) {
		this.colname = colname;
	}
	public String getPropname() {
		return propname;
	}
	public void setPropname(String propname) {
		this.propname = propname;
	}
	public Object getPropvalue() {
		return propvalue;
	}
	public void setPropvalue(Object propvalue) {
		this.propvalue = propvalue;
	}
	private String propname;
	private Object propvalue;

}
