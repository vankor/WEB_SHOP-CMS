package Model.dto_beans;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import Model.entity.Slider;
import Model.entity.SliderImage;
import Model.entity.Video;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class SliderBean implements FileEntityBean{
	
	private Integer id;
	private String name;
	private String folderpath;
	private List<SliderImageBean> images = new ArrayList<SliderImageBean>();
	private Boolean isonmain;

	
	public SliderBean(){}
	public SliderBean(Slider slider){
		id = slider.getId();
		name = slider.getName();
		folderpath = slider.getFolderpath();
		for(SliderImage sl:slider.getImages()){
			images.add(new SliderImageBean(sl));
		}
		isonmain = slider.getIsonmain();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty(message = "*Имя слайдера - обязателен для заполнения!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFolderpath() {
		return folderpath;
	}
	public void setFolderpath(String folderpath) {
		this.folderpath = folderpath;
	}
	
	@Valid
	public List<SliderImageBean> getImages() {
		return images;
	}
	public void setImages(List<SliderImageBean> images) {
		this.images = images;
	}
	public Boolean getIsonmain() {
		return isonmain;
	}
	public void setIsonmain(Boolean isonmain) {
		this.isonmain = isonmain;
	}
	@Override
	public List<Video> getVideos() {

		return null;
	}
	@Override
	public List<CommonsMultipartFile> getNewimages() {
		List<CommonsMultipartFile> newimg = new ArrayList<CommonsMultipartFile>();
		for(SliderImageBean imb : images){
			if(imb.getNewimage()!=null)
			newimg.add(imb.getNewimage());
		}
		
		return newimg;
	}
	@Override
	public void setNewimages(List<CommonsMultipartFile> newimages) {
		
		
	}
	@Override
	public CommonsMultipartFile getNewthumb() {

		return null;
	}
	@Override
	public void setNewthumb(CommonsMultipartFile newthumb) {

		
	}
	@Override
	public List<CommonsMultipartFile> getNewvideos() {
		return null;
	}
	@Override
	public void setNewvideos(List<CommonsMultipartFile> newvideos) {

		
	}
	@Override
	public String getThumb() {

		return null;
	}
	@Override
	public void setThumb(String thumb) {

		
	}

	

}
