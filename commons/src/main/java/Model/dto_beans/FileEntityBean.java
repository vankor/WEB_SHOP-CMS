package Model.dto_beans;

import java.util.List;

import Model.entity.Image;
import Model.entity.Video;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface FileEntityBean {
	public List<Video> getVideos();

	public List<CommonsMultipartFile> getNewimages();
	public void setNewimages(List<CommonsMultipartFile> newimages);
	public CommonsMultipartFile getNewthumb();
	public void setNewthumb(CommonsMultipartFile newthumb);
	public List<CommonsMultipartFile> getNewvideos();
	public void setNewvideos(List<CommonsMultipartFile> newvideos);
	public List<? extends Image> getImages();

	public String getThumb();
	public void setThumb(String thumb);

}
