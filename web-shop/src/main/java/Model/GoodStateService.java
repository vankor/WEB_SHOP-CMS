package Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodStateService  extends ServiceImpl<GoodState, Integer, GoodStateDAO>{
	@Autowired
	private GoodStateDAO gstDAO;
	

	public GoodState getByName(String name){
		return gstDAO.getGoodStateByName(name);
		
	}


}
