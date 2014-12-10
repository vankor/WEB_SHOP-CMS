package Model;

import java.util.List;

public interface PropSegmentDAO extends GenericDAO<PropSegment, Integer>{
	
	public void addPropSegment(PropSegment g);
	public void deletePropSegment(PropSegment g);
	public void deletePropSegmentById(int id);
	public List<PropSegment> getAllPropSegments();
	public PropSegment getPropSegmentById(int id);
	public void update(PropSegment k);
	

}