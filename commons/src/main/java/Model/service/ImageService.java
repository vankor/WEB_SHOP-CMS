package Model.service;


import Model.repository.ImageDAO;
import Model.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService extends ServiceImpl<Image, Integer, ImageDAO> {
	
	@Autowired
	private ImageDAO img;
	


}