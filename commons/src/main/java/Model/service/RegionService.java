package Model.service;

import Model.repository.RegionDAO;
import Model.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService extends ServiceImpl<Region, Integer, RegionDAO> {
	@Autowired
	private RegionDAO propDAO;

}


