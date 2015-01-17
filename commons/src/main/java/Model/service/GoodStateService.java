package Model.service;

import Model.repository.GoodStateDAO;
import Model.entity.GoodState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodStateService  extends ServiceImpl<GoodState, Integer, GoodStateDAO> {
	@Autowired
	private GoodStateDAO gstDAO;
	

	public GoodState getByName(String name){
		return gstDAO.getGoodStateByName(name);
		
	}


}
