package Model;

import java.util.List;

public interface FileEntity extends Entity{
	
	public String getThumb();
	public void setThumb(String thumb);
	public void addImage(Image im);
	public void removeImage(Image im);
	public List<? extends Image> getImages();
//	public void setImages(List images);
	public void addVideo(Video im);
	public List<Video> getVideos();
//	public void setVideos(List<Video> images);
	void removeVideo(Video vid);
}
