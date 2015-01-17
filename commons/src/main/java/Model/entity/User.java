package Model.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

import Model.others.AnonimBuck;
import Model.dto_beans.UserBean;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class User implements UserDetails, Comparable, Model.entity.Entity {
	
	public enum OrderingRules {
		NAME(1), USERNAME(2), COMMENTSCOUNT(3), BIRTHDATE(4), NICK(5), ORDERCOUNT(6);
		
		OrderingRules(int y){}
	}
	
	
	protected Integer id;
	protected String name;
	protected String username;
	protected String password;
	protected String ip;	
	protected boolean isEnabled;
	protected boolean isAccountNonExpired;
	protected boolean isAccountNonLocked;
	protected boolean isCredentialsNonExpired;
	protected Set<Role> roles = new HashSet<Role>();
	
	protected List<Bucket> buck = new ArrayList<Bucket>();
	
	protected Set<Comment> comments = new TreeSet<Comment>();
	
	protected Set<Order> orders = new TreeSet<Order>();
	protected Set<GoodItem> wishgoods = new TreeSet<GoodItem>();
	protected Set<GoodItem> historygoods = new TreeSet<GoodItem>();
	protected Set<GoodItem> watchgoods = new TreeSet<GoodItem>();
	protected Set<PhoneNumber> phonenumbers = new TreeSet<PhoneNumber>();
	
	protected transient AnonimBuck anonimbucket;
	protected String city;
	private Boolean isSubscribed;
	
	private Town town;

	private String nick;
	private String originpassword;
	private String regprovehash;
	private Boolean proved;
	protected String stringtown;	

	private Date birthdate;
	private Date regdate;
	
	private Adress adress;
	private Boolean isbanned = false;
	
	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Column(name = "isbanned")
	@Type(type = "boolean")
	public Boolean getIsbanned() {
		return isdeleted;
	}

	public void setIsbanned(Boolean isbanned) {
		this.isbanned = isbanned;
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
	@Column (name = "stringtown")
	public String getStringtown() {
		return stringtown;
	}
	public void setStringtown(String stringtown) {
		this.stringtown = stringtown;
	}
	
	@Column (name = "originpassword")
	public String getOriginpassword() {
		return originpassword;
	}
	public void setOriginpassword(String originpassword) {
		this.originpassword = originpassword;
	}
	
	@Column (name = "regprovehash", unique = true)
	public String getRegprovehash() {
		return regprovehash;
	}
	public void setRegprovehash(String regprovehash) {
		this.regprovehash = regprovehash;
	}
	
	@Column (name = "proved", nullable = false)
	@Type(type = "boolean")
	public Boolean getProved() {
		return proved;
	}
	public void setProved(Boolean proved) {
		this.proved = proved;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="regdate")
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Column (name = "ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	

	@ManyToOne
	@JoinColumn(name = "town_id")
	@JsonBackReference
	public Town getTown() {
		return town;
	}
	public void setTown(Town town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return "Логин: "+username+"; Имя: "+name;
	}
	
	
	@Column (name = "name")
	public String getName() {
		return name;
	}
	public void setLogin(String name) {
		this.name = name;
	}

	
	@OneToMany(targetEntity = Bucket.class, mappedBy = "usr", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	public List<Bucket> getBuck() {
		return buck;
	}
	public void setBuck(List<Bucket> buck) {
		this.buck = buck;
	}
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public void addBucket(Bucket l){
		buck.add(l);
		l.setUsr(this);
	}
	
	public void addComment(Comment l, GoodItem g){
		comments.add(l);
		l.setAuthor(this);
		g.addComment(l);
	}
	
	public void addWishgood(GoodItem g){
		wishgoods.add(g);
	}
	
	public void addWatchgood(GoodItem g){
		watchgoods.add(g);
	}
	
	public void addHistorygood(GoodItem g){
		historygoods.add(g);
	}
	
	
	public void removeComment(Comment l){
		comments.remove(l);
		l.getGood().removeComment(l);
	}
	
	
	
	@OneToMany(mappedBy = "usr", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	
	@Column(name = "issubscr")
	@Type(type = "boolean")
	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Transient
	public AnonimBuck getAnonimbucket() {
		return anonimbucket;
	}
	public void setAnonimbucket(AnonimBuck anonimbucket) {
		this.anonimbucket = anonimbucket;
	}
	
	
	@Transient
	public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
		for(Role r: roles){
			set.add(r);
		}
		return set;
	}

	@Column(name = "pass")
	public String getPassword() {
		return password;
	}
	@Column(name = "email")
	public String getUsername() {
		
		return username;
	}
	
	@Column(name = "isnonexpired", nullable = false)
	@Type(type = "boolean")
	public boolean isAccountNonExpired() {

		return isAccountNonExpired;
	}
	@Column(name = "isnonlocked", nullable = false)
	@Type(type = "boolean")
	public boolean isAccountNonLocked() {

		return isAccountNonLocked;
	}
	@Column(name = "isnoncredexpired", nullable = false)
	@Type(type = "boolean")
	public boolean isCredentialsNonExpired() {

		return isCredentialsNonExpired;
	}
	@Column(name = "enabled", nullable = false)
	@Type(type = "boolean")
	public boolean isEnabled() {

		return isEnabled;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", 
	joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Column(name="nick")
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	







	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birthdate")
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	@ManyToOne
	@JoinColumn(name="adress_id")
	@JsonBackReference
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_wishgoods", joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<GoodItem> getWishgoods() {
		return wishgoods;
	}
	public void setWishgoods(Set<GoodItem> wishgoods) {
		this.wishgoods = wishgoods;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_historygoods", joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<GoodItem> getHistorygoods() {
		return historygoods;
	}
	public void setHistorygoods(Set<GoodItem> historygoods) {
		this.historygoods = historygoods;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_watchgoods", joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<GoodItem> getWatchgoods() {
		return watchgoods;
	}
	public void setWatchgoods(Set<GoodItem> watchgoods) {
		this.watchgoods = watchgoods;
	}

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_phones", joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "phone_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<PhoneNumber> getPhonenumbers() {
		return phonenumbers;
	}
	public void setPhonenumbers(Set<PhoneNumber> phonenumbers) {
		this.phonenumbers = phonenumbers;
	}
	
	public void addPhonenumber(PhoneNumber phonenumber) {
		this.phonenumbers.add(phonenumber);
	}
	@Override
	public int compareTo(Object o) {
		User othergood = null;
		if(o instanceof User){	
			othergood = (User)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.name.compareTo(othergood.name);
	}
	public void setFromUserBean(UserBean user) throws ParseException {
		this.birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthdate());		
		this.regdate = new SimpleDateFormat("yyyy-MM-dd").parse(user.getRegdate()+" "+user.getRegtime());	
		this.name = user.getName();
		this.username = user.getUsername();
		this.proved = user.getProved();
		this.isbanned = user.getIsbanned();
		this.isSubscribed = user.getIsSubscribed();
		this.originpassword = user.getOriginpassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		this.password = encoder.encodePassword(user.getOriginpassword(), null);
		this.nick = user.getNick();
		
		 
	}
	
}
