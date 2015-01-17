package Model.repository;

import Model.entity.Slider;
import Model.entity.SliderImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SliderDAOImpl extends GenericHibTemplateDAOImpl<Slider, Integer> implements SliderDAO {

	@Autowired
	private SliderImageDAO sldimDAO;
	
	@Override
	@Transactional
	public Integer save(Slider entity) {
		if(!entity.getImages().isEmpty()){
			for(SliderImage sldim : entity.getImages()){
				sldim.setId(sldimDAO.save(sldim));
			}
		}
		return super.save(entity);
	}
	


}
