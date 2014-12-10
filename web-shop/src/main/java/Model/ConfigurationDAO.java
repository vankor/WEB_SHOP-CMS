package Model;

import java.util.List;

public interface ConfigurationDAO extends GenericDAO<Configuration, Integer>{
	void setActiveConfigById(Integer id);
	void setActiveConfig(Configuration conf);
	Configuration getActiveConfiguration();
}
