package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VideoService extends ServiceImpl<Value, Integer, ValueDAO> {
	
	@Autowired
	private VideoDAO vid;
	

	/*@Transactional
	public void addMedia(MediaFile f){
		vid.addVideo((Video)f);
	}

	@Transactional
	public List<MediaFile> getAllMedias(){
		return toMediaList(vid.getAllVideos());
	}
	
	@Transactional
	public void deleteMediaById(Integer f){
		vid.deleteVideoById(f);
	}
	
	@Transactional
	public Video getMediaById(Integer id){
		return vid.getVideoById(id);
	}
	
	@Transactional
	public void update(MediaFile video){
		 vid.update((Video)video);
	}
	
	@Transactional
	public Integer getLastId() {

		return vid.getLastId();
		
	}
	
	private List<MediaFile> toMediaList(List<Video> list){
		List<MediaFile> listmedia = new ArrayList<MediaFile>();
		for(Object o:list){
				MediaFile h = (MediaFile)o;
				listmedia.add(h);
				
			}
		return listmedia ;
	}

	*/
	

}