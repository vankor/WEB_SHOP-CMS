package Model.entity;

import Model.entity.Entity;
import Model.entity.Image;
import Model.entity.Video;

import java.util.List;

public interface FileEntity extends Entity {
	
	public String getThumb();
	public void setThumb(String thumb);
	public void addImage(Image im);
	public void removeImage(Image im);
	public List<? extends Image> getImages();

	public void addVideo(Video im);
	public List<Video> getVideos();

	void removeVideo(Video vid);
}
