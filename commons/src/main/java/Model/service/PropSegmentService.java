package Model.service;

import Model.repository.PropSegmentDAO;
import Model.entity.PropSegment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropSegmentService extends ServiceImpl<PropSegment, Integer, PropSegmentDAO> {
	
	@Autowired
	PropSegmentDAO propDAO;

}
