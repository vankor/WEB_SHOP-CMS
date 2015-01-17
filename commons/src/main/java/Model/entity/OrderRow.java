package Model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import Model.others.GoodCollection;
import Model.others.Row;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table (name = "orderrows")
public class OrderRow  implements Model.entity.Entity, Row {
	private Integer id;
	private GoodItem good;
	private Integer goodcount;
	private Order ord;
	private Boolean proved;
	
	private Boolean returned;
	private Boolean isdeleted;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((good == null) ? 0 : good.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderRow other = (OrderRow) obj;
		if (good == null) {
			if (other.good != null)
				return false;
		} else if (!good.equals(other.good))
			return false;
		return true;
	}
	OrderRow(GoodItem good, Integer goodcount){
		this.good =good;
		this.goodcount = goodcount;
		
	}
	
	public OrderRow(){	}
	
	public OrderRow(Row bean){	
		this.good = bean.getGood();
		this.goodcount = bean.getGoodcount();
	}
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "good_id")
	@JsonBackReference
	public GoodItem getGood() {
		return good;
	}
	public void setGood(GoodItem good) {
		this.good = good;
	}

	
	@Column(name = "good_count")
	public Integer getGoodcount() {
		return goodcount;
	}
	
	public void setGoodcount(Integer goodcount) {
		this.goodcount = goodcount;
	}
	
	@Type(type = "boolean")
	@Column(name = "proved")
	public Boolean getProved() {
		return proved;
	}
	public void setProved(Boolean proved) {
		this.proved = proved;
	}
	
	@Type(type = "boolean")
	@Column(name = "returned")
	public Boolean getReturned() {
		return returned;
	}
	public void setReturned(Boolean returned) {
		this.returned = returned;
	}
	
	@Id
	@GenericGenerator(name = "increment", strategy = "increment") 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonBackReference
	public Order getOrd() {
		return ord;
	}
	public void setOrd(Order ord) {
		this.ord = ord;
	}
	
	
	@Transient
	public GoodCollection getGoodset() {

		return getOrd();
	}
	@Transient
	public void setGoodset(GoodCollection ord) {
		this.ord = (Order) ord;
		
	}
	@Override
	public int compareTo(Object o) {
		OrderRow otherrow = null;
		if(o instanceof OrderRow){	
			otherrow = (OrderRow)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		if(this.id!=null && otherrow.id!=null){
			return this.id.compareTo(otherrow.id);
		}
		return this.good.compareTo(otherrow.good);
		
	}
	
	@Type(type = "boolean")
	@Column(name = "isdeleted")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	
	

}
