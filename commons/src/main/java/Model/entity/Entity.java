package Model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public interface Entity {
	public Integer getId();
	public void setId(Integer id);
	public Boolean getIsdeleted();
	public void setIsdeleted(Boolean b);
}
