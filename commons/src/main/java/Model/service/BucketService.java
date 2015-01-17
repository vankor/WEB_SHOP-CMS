package Model.service;

import Model.repository.BucketDAO;
import Model.entity.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BucketService extends ServiceImpl<Bucket, Integer, BucketDAO> {
	
	@Autowired
	private BucketDAO bct;
	

	

}
