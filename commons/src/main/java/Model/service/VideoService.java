package Model.service;


import Model.repository.ValueDAO;
import Model.repository.VideoDAO;
import Model.entity.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VideoService extends ServiceImpl<Value, Integer, ValueDAO> {
	
	@Autowired
	private VideoDAO vid;
	


	

}