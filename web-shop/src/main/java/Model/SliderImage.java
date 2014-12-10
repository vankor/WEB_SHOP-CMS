package Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "sliderimages")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class SliderImage extends Image{
	
	private String href;
	private String text;
	private Slider slider;
	
	public SliderImage(String databasepath) {
		super(databasepath);
		
	}
	public SliderImage() {
		// TODO Auto-generated constructor stub
	}
	@ManyToOne
	@JoinColumn(name="slider_id")
	@NotNull(message = "*Слайдер должен быть определен!")
	public Slider getSlider() {
		return slider;
	}
	public void setSlider(Slider slider) {
		this.slider = slider;
	}
	
	
	@Column(name="href")
	@NotEmpty(message = "*Href для ссылки - обязателен для заполнения!")
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	@Column(name="text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "SliderImage [href=" + href + ", text=" + text +", url=" + url +", alt=" + alt +"]";
	}
	public void fillfrombean(SliderImageBean sim){
		href = sim.getHref();
		text = sim.getText();	
		url = sim.getUrl();
		alt = sim.getAlt();
		priority = sim.getPriority();
		
	}
	
}
