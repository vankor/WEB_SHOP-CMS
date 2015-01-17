package Model.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

import Model.CommentBean;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "comments")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class Comment implements Comparable, Model.Entity{
	
	
	private Integer id;
	private GoodItem good;
	
	private Date datetime;
	private String text;
	private User author;
//	private Integer note;
	
//	private Integer parent_id;
	private String authorname;
	private String authoremail;
	private String plus;
	private String minus;
	private Integer note;
	
	private Boolean isproved = false;

	private Comment parent_comment;

	private transient Set<Comment> chieldcomms = new TreeSet<Comment>(new Comparator<Comment>(){
		@Override
		public int compare(Comment o1, Comment o2) {
			// TODO Auto-generated method stub
			return o1.getDatetime().compareTo(o2.getDatetime());
		}
	});
	
	private Boolean isdeleted = false;
	
	public void constructfrombean(CommentBean comment) throws ParseException{
		
			
		datetime = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(comment.getDate()+" "+comment.getTime());	
			text = comment.getText();
			authorname = comment.getAuthorname();
			authoremail = comment.getAuthoremail();
			plus = comment.getPlus();
			minus = comment.getMinus();
			note = comment.getNote();
			isproved = comment.getIsproved();
		
	
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
	@GenericGenerator(name="increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", Time=" + datetime.toString() + ", text=" + text + "]";
	}
	
	@ManyToOne
	@JoinColumn(name = "good_id", nullable = false)
	@JsonBackReference
	public GoodItem getGood() {
		return good;
	}
	
	public void setGood(GoodItem goodItem) {
		this.good = goodItem;
		
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datetime")
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date time) {
		this.datetime = time;
	}
	
	@Type(type="boolean")
	@Column(name = "isproved")
	public Boolean getIsproved() {
		return isproved;
	}
	public void setIsproved(Boolean isproved) {
		this.isproved = isproved;
	}
	
	@Column(name = "text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
/*	@Column (name = "parent_id")
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}*/
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	@JsonBackReference
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	@Transient
	@JsonManagedReference
	public Set<Comment> getChieldcomms() {
		return chieldcomms;
	}
	public void setChieldcomms(Set<Comment> chieldcoms) {
		this.chieldcomms = chieldcoms;
	}
	
	@Column (name = "authorname")
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	
	@Column (name = "authoremail")
	public String getAuthoremail() {
		return authoremail;
	}
	public void setAuthoremail(String authoremail) {
		this.authoremail = authoremail;
	}
	
	@Column (name = "plus")
	public String getPlus() {
		return plus;
	}
	public void setPlus(String plus) {
		this.plus = plus;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	public Comment getParent_comment() {
		return parent_comment;
	}
	public void setParent_comment(Comment parent_comment) {
		this.parent_comment = parent_comment;
	}
	
	@Column (name = "minus")
	public String getMinus() {
		return minus;
	}
	public void setMinus(String minus) {
		this.minus = minus;
	}
	
	@Column (name = "note")
	public Integer getNote() {
		return note;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	@Override
	public int compareTo(Object arg0) {
		Comment othercomm = null;
		Date date1 = new Date();
		Date date2 = new Date();
		if(arg0 instanceof Comment){	
			othercomm = (Comment)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
	//	try {
	//		date1 = format.parse(time);
	//		date2 = format.parse(othercomm.time);
//		} catch (ParseException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
		
		return this.datetime.compareTo(othercomm.datetime);
	}


}
