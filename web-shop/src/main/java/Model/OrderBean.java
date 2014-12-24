package Model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class OrderBean {

	private String clientname;
//	private  String clientphone_number;
	private List<PhoneNumber> clientphones = new ArrayList<PhoneNumber>();
	private  String clientemail;
	private  String clientcomment;
	private  String clientsrochnost;
	private  String client_delivdate;
	private  String client_delivtime;
	private  String clienttown_name;
	private  Integer clienttown;
	private  Integer clientoplata;
	private  Integer clientdeliverytype;
	private  String type_street;
	private  String street;
	private  String house;
	private  String flor;
	private  String room;
	private  String nzanos;
	private  String netaj;
	private  String nlift;
	private  String sessid;
	private  String action;
	private  String srok_cred;
	private  String client_company;
	private  String client_okpo;
	private  Integer filial_adress;
	
	@NotNull(message = "*Заполните Ваше имя, пожалуйста")
	@NotEmpty(message = "*Заполните Ваше имя, пожалуйста")
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	
	@NotNull(message = "*Заполните Ваш е-мейл, пожалуйста")
	@NotEmpty(message = "*Заполните Ваш е-мейл, пожалуйста")
	@Email(message = "*Е-мейл должен быть в формате ***@***.***")
	public String getClientemail() {
		return clientemail;
	}
	public List<PhoneNumber> getClientphones() {
		return clientphones;
	}
	
	
	public void setClientphones(List<PhoneNumber> clientphones) {
		this.clientphones = clientphones;
	}
	public void setClientemail(String clientemail) {
		this.clientemail = clientemail;
	}
	public String getClientcomment() {
		return clientcomment;
	}
	public void setClientcomment(String clientcomment) {
		this.clientcomment = clientcomment;
	}
	public String getClientsrochnost() {
		return clientsrochnost;
	}
	public void setClientsrochnost(String clientsrochnost) {
		this.clientsrochnost = clientsrochnost;
	}
	
	@NotNull (message = "*Активна срочная доставка - задайте дату!")
	@NotEmpty (message = "*Активна срочная доставка - задайте дату!")
	@Pattern(regexp= "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "*Дата окончания в формате yyyy-MM-dd!")
	public String getClient_delivdate() {
		return client_delivdate;
	}
	public void setClient_delivdate(String client_delivdate) {
		this.client_delivdate = client_delivdate;
	}
	
/*	@NotNull (message = "*Активна срочная доставка - задайте время!")
	@NotEmpty (message = "*Активна срочная доставка - задайте время!")*/
	@Pattern(regexp= "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "*Время окончания в формате hh:mm!")
	public String getClient_delivtime() {
		return client_delivtime;
	}
	public void setClient_delivtime(String client_delivtime) {
		this.client_delivtime = client_delivtime;
	}
	public String getClienttown_name() {
		return clienttown_name;
	}
	public void setClienttown_name(String clienttown_name) {
		this.clienttown_name = clienttown_name;
	}

	public Integer getClienttown() {
		return clienttown;
	}
	public void setClienttown(Integer clienttown) {
		this.clienttown = clienttown;
	}
	public Integer getClientoplata() {
		return clientoplata;
	}
	public void setClientoplata(Integer clientoplata) {
		this.clientoplata = clientoplata;
	}
	public Integer getClientdeliverytype() {
		return clientdeliverytype;
	}
	public void setClientdeliverytype(Integer clientdeliverytype) {
		this.clientdeliverytype = clientdeliverytype;
	}
	public String getType_street() {
		return type_street;
	}
	public void setType_street(String type_street) {
		this.type_street = type_street;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getFlor() {
		return flor;
	}
	public void setFlor(String flor) {
		this.flor = flor;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getNzanos() {
		return nzanos;
	}
	public void setNzanos(String nzanos) {
		this.nzanos = nzanos;
	}
	public String getNetaj() {
		return netaj;
	}
	public void setNetaj(String netaj) {
		this.netaj = netaj;
	}
	public String getNlift() {
		return nlift;
	}
	public void setNlift(String nlift) {
		this.nlift = nlift;
	}
	public String getSessid() {
		return sessid;
	}
	public void setSessid(String sessid) {
		this.sessid = sessid;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSrok_cred() {
		return srok_cred;
	}
	public void setSrok_cred(String srok_cred) {
		this.srok_cred = srok_cred;
	}
	public String getClient_company() {
		return client_company;
	}
	public void setClient_company(String client_company) {
		this.client_company = client_company;
	}
	public String getClient_okpo() {
		return client_okpo;
	}
	public void setClient_okpo(String client_okpo) {
		this.client_okpo = client_okpo;
	}
	public Integer getFilial_adress() {
		return filial_adress;
	}
	public void setFilial_adress(Integer filial_adress) {
		this.filial_adress = filial_adress;
	}
	
	
	
}
