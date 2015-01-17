package Model.service;

import Model.entity.GoodStatus;
import Model.repository.GoodStatusDAO;
import Model.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodStatusService  extends ServiceImpl<GoodStatus, Integer, GoodStatusDAO> {
	@Autowired
	private GoodStatusDAO gstDAO;
	

	public GoodStatus getByName(String name){
		return gstDAO.getGoodStatusByName(name);
		
	}


}
