package Model.dto_beans;

import Model.entity.Comment;
import Model.entity.GoodItem;
import Model.entity.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentBean {

	private Integer id;
	private GoodItem good;
	
	private String date;
	private String time;
	private String text;
	private User author;
	
//	private Integer parent_id;
	private String authorname;
	private String authoremail;
	private String plus;
	private String minus;
	private Integer note;
	
	private Boolean isproved = false;

	private Comment parent_comment;

	private transient List<Comment> chieldcomms = new ArrayList<Comment>();
	
	public void constructfromcomment(Comment comment){
		id = comment.getId();
		if(comment.getDatetime()!=null){
		date = new SimpleDateFormat("yyyy-MM-dd").format(comment.getDatetime());
		time = new SimpleDateFormat("hh:mm").format(comment.getDatetime());
		}
		text = comment.getText();
		authorname = comment.getAuthorname();
		authoremail = comment.getAuthoremail();
		plus = comment.getPlus();
		minus = comment.getMinus();
		note = comment.getNote();
		isproved = comment.getIsproved();
		author = comment.getAuthor();
		good = comment.getGood();
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GoodItem getGood() {
		return good;
	}

	public void setGood(GoodItem good) {
		this.good = good;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public String getAuthoremail() {
		return authoremail;
	}

	public void setAuthoremail(String authoremail) {
		this.authoremail = authoremail;
	}

	public String getPlus() {
		return plus;
	}

	public void setPlus(String plus) {
		this.plus = plus;
	}

	public String getMinus() {
		return minus;
	}

	public void setMinus(String minus) {
		this.minus = minus;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Boolean getIsproved() {
		return isproved;
	}

	public void setIsproved(Boolean isproved) {
		this.isproved = isproved;
	}

	public Comment getParent_comment() {
		return parent_comment;
	}

	public void setParent_comment(Comment parent_comment) {
		this.parent_comment = parent_comment;
	}

	public List<Comment> getChieldcomms() {
		return chieldcomms;
	}

	public void setChieldcomms(List<Comment> chieldcomms) {
		this.chieldcomms = chieldcomms;
	}
	
	
	
}
