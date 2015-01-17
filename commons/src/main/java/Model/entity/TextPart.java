package Model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import Model.others.NewsPart;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "textparts")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TextPart implements Comparable, Model.entity.Entity, NewsPart {
	private Integer id;
	private String text;
	private String textkey;
	
	private transient String value;
	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	
	@Column(name = "textkey")	
	public String getTextkey() {
		return textkey;
	}
	public void setTextkey(String textkey) {
		this.textkey = textkey;
	}
	@Id
	@GenericGenerator(name = "id", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="text", length = 1000000)
	@NotNull (message = "*Текст обязателен к заполнению!")
	@NotEmpty (message = "*Текст обязателен к заполнению!")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public int compareTo(Object o) {

		return 0;
	}
	@Override
	@Transient
	public String getValue() {

		return getText();
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
