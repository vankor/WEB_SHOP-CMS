package Model.service;

import Model.repository.PropertyDAO;
import Model.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService extends ServiceImpl<Property, Integer, PropertyDAO> {
	@Autowired
	private PropertyDAO propDAO;
	
}
