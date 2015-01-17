package Model.repository;

import Model.entity.Configuration;

public interface ConfigurationDAO extends GenericDAO<Configuration, Integer>{
	void setActiveConfigById(Integer id);
	void setActiveConfig(Configuration conf);
	Configuration getActiveConfiguration();
}
