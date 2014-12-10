package Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropSegmentService extends ServiceImpl<PropSegment, Integer, PropSegmentDAO>{
	
	@Autowired
	PropSegmentDAO propDAO;
	
/*	public void add(PropSegment g){
		propDAO.addPropSegment(g);
		
	}
	public void delete(PropSegment g){
		propDAO.deletePropSegment(g);
	}
	public void deleteById(Integer id){
		propDAO.deletePropSegmentById(id);
		
	}
	public List<PropSegment> getAll(){
		return propDAO.getAllPropSegments();
		
	}
	public PropSegment getById(Integer id){
		return propDAO.getPropSegmentById(id);
		
	}
	public void update(PropSegment k){
		propDAO.update(k);
	}
	
	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
