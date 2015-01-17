package Model.dto_beans;

import Model.entity.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserBean {
	
	protected Integer id;
	protected List<Role> roles = new ArrayList<Role>();
	
	private String name;
	private String birthdate;
	private String nick;
	

	private String phonenumber;
	private String adress;
	
	

	private String username;
	
	private Adress useradress;
	private String originpassword;
	private Boolean isSubscribed;
	private Town town;
	private String regdate;
	private String regtime;
	private Boolean isbanned;
	private Boolean proved;
	
	
	protected List<Order> orders = new ArrayList<Order>();
	protected List<GoodItem> wishgoods = new ArrayList<GoodItem>();
	protected List<GoodItem> historygoods = new ArrayList<GoodItem>();
	protected List<GoodItem> watchgoods = new ArrayList<GoodItem>();
	protected List<PhoneNumber> phonenumbers = new ArrayList<PhoneNumber>();
    protected List<Bucket> buck = new ArrayList<Bucket>();;
	protected List<Comment> comments = new ArrayList<Comment>();
	
	
	public void constructfromuser(User user){
		name = user.getName();
		id = user.getId();
		if(user.getBirthdate()!=null)
		birthdate = new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthdate());
		nick = user.getNick();
		
		phonenumbers = new ArrayList(user.getPhonenumbers());
		useradress = user.getAdress();
		originpassword = user.getOriginpassword();
		isSubscribed = user.getIsSubscribed();
		town = user.getTown();
		if(user.getRegdate()!=null){
		regdate = new SimpleDateFormat("yyyy-MM-dd").format(user.getRegdate());
		regtime = new SimpleDateFormat("hh:mm").format(user.getRegdate());}
		isbanned = user.getIsbanned();
		proved = user.getProved();
		orders = new ArrayList<Order>(user.getOrders());
		wishgoods = new ArrayList<GoodItem>(user.getWishgoods());
		historygoods = new ArrayList<GoodItem>(user.getHistorygoods());
		watchgoods = new ArrayList<GoodItem>(user.getWatchgoods());
		buck = user.getBuck();
		comments = new ArrayList<Comment>(user.getComments());
		username = user.getUsername();
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<GoodItem> getWishgoods() {
		return wishgoods;
	}
	public void setWishgoods(List<GoodItem> wishgoods) {
		this.wishgoods = wishgoods;
	}
	public List<GoodItem> getHistorygoods() {
		return historygoods;
	}
	public void setHistorygoods(List<GoodItem> historygoods) {
		this.historygoods = historygoods;
	}
	public List<GoodItem> getWatchgoods() {
		return watchgoods;
	}
	public void setWatchgoods(List<GoodItem> watchgoods) {
		this.watchgoods = watchgoods;
	}
	public List<PhoneNumber> getPhonenumbers() {
		return phonenumbers;
	}
	public void setPhonenumbers(List<PhoneNumber> phonenumbers) {
		this.phonenumbers = phonenumbers;
	}
	public List<Bucket> getBuck() {
		return buck;
	}
	public void setBuck(List<Bucket> buck) {
		this.buck = buck;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public Adress getUseradress() {
		return useradress;
	}

	public void setUseradress(Adress useradress) {
		this.useradress = useradress;
	}

	public String getOriginpassword() {
		return originpassword;
	}

	public void setOriginpassword(String originpassword) {
		this.originpassword = originpassword;
	}

	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public Boolean getIsbanned() {
		return isbanned;
	}

	public void setIsbanned(Boolean isbanned) {
		this.isbanned = isbanned;
	}

	public Boolean getProved() {
		return proved;
	}

	public void setProved(Boolean proved) {
		this.proved = proved;
	}

}
