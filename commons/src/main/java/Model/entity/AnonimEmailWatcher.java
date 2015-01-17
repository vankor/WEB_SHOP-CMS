package Model.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="anonimemails")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class AnonimEmailWatcher implements Comparable, Model.entity.Entity {
	private Integer id;
	private String email;
	private Set <GoodItem> watchgoods = new TreeSet<GoodItem>();
	
	private Boolean isdeleted;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Id
	@GenericGenerator(name="increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addGood(GoodItem g){
		watchgoods.add(g);
	}
	
	@ManyToMany
	@JoinTable(name = "email_watchgoods", joinColumns = {@JoinColumn(name = "email_id", nullable = false, updatable = false)}, 
			inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false,  updatable = false)})
	public Set<GoodItem> getWatchgoods() {
		return watchgoods;
	}
	public void setWatchgoods(Set<GoodItem> watchgoods) {
		this.watchgoods = watchgoods;
	}
	@Override
	public int compareTo(Object o) {

		return 0;
	}
	

}
