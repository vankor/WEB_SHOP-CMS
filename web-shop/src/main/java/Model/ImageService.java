package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService extends ServiceImpl<Image, Integer, ImageDAO>{
	
	@Autowired
	private ImageDAO img;
	

/*	@Transactional
	public void add(Image f){
		img.addImage(f);
	}

	@Transactional
	public List<Image> getAll(){
		return toMediaList(img.getAllImages());
	}
	
	@Transactional
	public void deleteById(Integer f){
		img.deleteImageById(f);
	}
		
	@Transactional
	public void update(Image image){
		 img.update(image);
	}
	
	@Transactional
	public Integer getLastId(){
		 return img.getLastId();
	}

	
	@Transactional
	public Image getById(Integer id) {
		return img.getImageById(id);
	}
	
	private List<Image> toMediaList(List<Image> list){
		List<Image> listmedia = new ArrayList<Image>();
		for(Object o:list){
			Image h = (Image)o;
				listmedia.add(h);
				
			}
		return listmedia ;
	}

	*/
}