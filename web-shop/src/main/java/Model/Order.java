package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table (name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class Order implements GoodCollection, Comparable, Model.Entity{




	private Integer id;
//	private List<GoodItem> goods = new ArrayList<GoodItem>();
	private Set<OrderRow> orderrows = new TreeSet<OrderRow>();
	private User usr;
	private Date time;
	private Double amount;
	private String clientname;
	private String clientemail;
	private String cred_term;
	private List<PhoneNumber> client_phones = new ArrayList<PhoneNumber>();
	private String comment;
//	private Date delivdate;
	private Date delivtime;
	private Town clienttown;
	private PayType paytype;
	private DeliveryType deliverytype;
	private Adress adress;
	private String sessid;
	private String client_company;
	private String client_okpo;
	
	private Boolean isdeleted = false;
	
	
	@Override
	public String toString() {
		return id.toString();
	}
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
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

	
	
//	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "order_good", 
//	joinColumns = {@JoinColumn(name = "order_id", nullable = false, updatable = false)}, 
//	inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
//	@Fetch(value = FetchMode.SUBSELECT)
	
//	public List<GoodItem> getGoods() {
//		return goods;
//	}

//	public void setGoods(List<GoodItem> goods) {
//		this.goods = goods;
//	}
	
//	public void addGood(GoodItem h){
//		goods.add(h);
//	}
	
	

	@ManyToOne	
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}
		

	
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

	public static void main(String[] args) {
		ArrayList <GoodItem> h = new ArrayList<GoodItem>();
		GoodItem h1 = new GoodItem();
		h1.setDescription("mob1");
		h1.setName("mobile");
		h1.setId(1);
		h1.setPrice(240.0);
		GoodItem h2 = new GoodItem();
		h2.setDescription("mob1");
		h2.setName("mobile");
		h2.setId(1);
		GoodItem h3 = new GoodItem();
		h3.setDescription("mob2");
		h3.setName("mobile");
		h3.setId(2);
		h3.setPrice(50.0);
		GoodItem h4 = new GoodItem();
		h4.setDescription("mob2");
		h4.setName("mobile");
		h4.setId(2);
		GoodItem h5 = new GoodItem();
		h5.setDescription("mob3");
		h5.setName("mobile");
		h5.setId(3);
		h5.setPrice(150.0);
		
		h.add(h1);
		h.add(h1);
		h.add(h1);
		h.add(h1);
		h.add(h1);
		h.add(h3);
		h.add(h3);
		h.add(h3);
		h.add(h5);
		h.add(h5);
		h.add(h5);
		Order g = new Order();
//		g.setGoods(h);
//		HashMap <GoodItem, Integer> o = g.createReport();
//		Iterator i = o.entrySet().iterator();
				
//		while(i.hasNext()){
//			Entry<GoodItem, Integer> entry = (Entry<GoodItem, Integer>) i.next();
//			GoodItem j = entry.getKey();
	//		Integer k = entry.getValue();
			
	//		System.out.println(j.getPrice()+"      "+k);
	//	}
		
	//	System.out.println(g.getSumValue());
	}


	
	
	
//	public List<Row> getRows() {
//		return rows;
//	}

//	public void setRows(List<Row> rows) {
//		this.rows = rows;
//	}

	
	@OneToMany (mappedBy = "ord", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	public Set<OrderRow> getOrderrows() {
		return orderrows;
	}

	public void setOrderrows(Set<OrderRow> orderrows) {
		this.orderrows = orderrows;
	}

	
	@Override
	public void addRows(Row k) {
		orderrows.add((OrderRow)k);
		
	}

	@Transient
	public Collection<Row> getRows() {
		Set<Row> lst = new TreeSet<Row>();
		for(OrderRow r: orderrows){
			lst.add((Row)r);
		}
		return lst;
	}

	@Transient
	public void setRows(Collection<Row> goods) {
		orderrows = new TreeSet<OrderRow>();
		for(Row r: goods){
				OrderRow ordrow = new OrderRow(r);
				ordrow.setOrd(this);
				orderrows.add(ordrow);
			
		}
		
	}


	@Column(name = "clientname")
	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	
	@Column(name = "comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

//	@Temporal(TemporalType.DATE)
//	@Column(name = "delivdate")
//	public Date getDelivdate() {
//		return delivdate;
//	}

//	public void setDelivdate(Date delivdate) {
//		this.delivdate = delivdate;
//	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delivtime")
	public Date getDelivtime() {
		return delivtime;
	}

	public void setDelivtime(Date delivtime) {
		this.delivtime = delivtime;
	}

	@ManyToOne
	@JoinColumn(name = "clienttown_id")
	@JsonBackReference
	public Town getClienttown() {
		return clienttown;
	}

	public void setClienttown(Town clienttown) {
		this.clienttown = clienttown;
	}

	@ManyToOne
	@JoinColumn(name = "paytype_id")
	@JsonBackReference
	public PayType getPaytype() {
		return paytype;
	}

	public void setPaytype(PayType paytype) {
		this.paytype = paytype;
	}

	@ManyToOne
	@JoinColumn(name = "deliverytype_id")
	@JsonBackReference
	public DeliveryType getDeliverytype() {
		return deliverytype;
	}

	public void setDeliverytype(DeliveryType deliverytype) {
		this.deliverytype = deliverytype;
	}

	@ManyToOne
	@JoinColumn(name = "adress_id")
	@JsonBackReference
	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	@Column(name = "sessid")
	public String getSessid() {
		return sessid;
	}

	public void setSessid(String sessid) {
		this.sessid = sessid;
	}

	@Column(name = "client_company")
	public String getClient_company() {
		return client_company;
	}

	public void setClient_company(String client_company) {
		this.client_company = client_company;
	}

	@Column(name = "client_okpo")
	public String getClient_okpo() {
		return client_okpo;
	}

	public void setClient_okpo(String client_okpo) {
		this.client_okpo = client_okpo;
	}

	@Column(name = "clientemail")
	public String getClientemail() {
		return clientemail;
	}

	public void setClientemail(String clientemail) {
		this.clientemail = clientemail;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "order_phones", joinColumns = {@JoinColumn(name = "order_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "phone_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<PhoneNumber> getClient_phones() {
		return client_phones;
	}

	public void setClient_phones(List<PhoneNumber> client_phones) {
		this.client_phones = client_phones;
	}
	
	public void addClient_phone(PhoneNumber ph){
		client_phones.add(ph);
	}
	
	@Column(name = "cred_term")
	public String getCred_term() {
		return cred_term;
	}

	public void setCred_term(String cred_term) {
		this.cred_term = cred_term;
	}

	@Override
	public int compareTo(Object arg0) {
		Order otherord = null;
		if(arg0 instanceof Order){	
			otherord = (Order)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.time.compareTo(otherord.time);
	}


}
