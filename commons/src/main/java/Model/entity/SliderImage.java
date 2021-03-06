package Model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import Model.dto_beans.SliderImageBean;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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

	}
	@ManyToOne
	@JoinColumn(name="slider_id")
	@NotNull(message = "*������� ������ ���� ���������!")
	public Slider getSlider() {
		return slider;
	}
	public void setSlider(Slider slider) {
		this.slider = slider;
	}
	
	
	@Column(name="href")
	@NotEmpty(message = "*Href ��� ������ - ���������� ��� ����������!")
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
