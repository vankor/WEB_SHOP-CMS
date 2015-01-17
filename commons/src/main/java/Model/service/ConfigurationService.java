package Model.service;

import Model.repository.ConfigurationDAO;
import Model.repository.SliderDAO;
import Model.entity.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfigurationService  extends ServiceImpl<Configuration, Integer, ConfigurationDAO> {
	
	@Autowired
	private ConfigurationDAO confDAO;
	
	@Autowired
	private SliderDAO sldDAO;
	
	@Override
	@Transactional
	public void update(Configuration entity) {
		
		sldDAO.update(entity.getSlider());
		super.update(entity);
	}
	public void setActiveConfigById(Integer id){
		confDAO.setActiveConfigById(id);
	}
	public void setActiveConfig(Configuration conf){
		confDAO.setActiveConfig(conf);
	}
	public Configuration getActiveConfiguration(){
		return confDAO.getActiveConfiguration();
		
	}
}
