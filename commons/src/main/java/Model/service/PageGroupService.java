package Model.service;

import java.util.Set;

import Model.entity.PageGroup;
import Model.repository.PageGroupDAO;
import Model.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageGroupService  extends ServiceImpl<PageGroup, Integer, PageGroupDAO> {
	@Autowired
	PageGroupDAO pgrDAO;
	
	public Set<PageGroup> getFooterPagegroups(){
		return pgrDAO.getFooterPagegroups();
		
	}
}
