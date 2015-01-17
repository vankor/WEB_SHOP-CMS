package Model.entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import Model.GoodCollection;
import Model.Row;
import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


  @Entity
  @Table(name = "buckets")
  @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
  property = "@id")
public class Bucket implements GoodCollection, Comparable, Model.Entity {
	private Integer id;
	private String name;
	private List <GoodItem> goods= new ArrayList<GoodItem>();
	private User usr;
//	private List <OrderRowBean> orderrows= new ArrayList<OrderRowBean>();
	
	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
		
	@ManyToMany (targetEntity = GoodItem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "bucket_good", joinColumns = {@JoinColumn(name = "bucket_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	public List <GoodItem> getGoods(){
	return goods;
	}
	
	public void setGoods(List<GoodItem> goods) {
		this.goods = goods;
	}
	Bucket(String name){
		this.name = name;
		
	}
	
	public Bucket(){}
	
	public void addGood(GoodItem k){
		goods.add(k);
		
	}
	
	public void deleteGood(GoodItem k){
		goods.remove(k);
	}
	
	@Id
	@GenericGenerator(name="increment", strategy = "increment")
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

	@Override
	public void addRows(Row k) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	public Collection<Row> getRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void setRows(Collection<Row> goods) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}



//	public Iterator createIterator(){
//		return goods.iterator();
//	}

	
//	public void addOrderrows(OrderRowBean k) {
//		orderrows.add(k);
		
//	}
	
	
//	public List<OrderRowBean> getOrderrows() {
//		return orderrows;
//	}
	
	
//	public void setOrderrows(List<OrderRowBean> orderrows) {
//		this.orderrows = orderrows;
//	}
	
	
}
