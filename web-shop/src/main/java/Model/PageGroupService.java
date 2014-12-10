package Model;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageGroupService  extends ServiceImpl<PageGroup, Integer, PageGroupDAO>{
	@Autowired
	PageGroupDAO pgrDAO;
	
	public Set<PageGroup> getFooterPagegroups(){
		return pgrDAO.getFooterPagegroups();
		
	}
}
