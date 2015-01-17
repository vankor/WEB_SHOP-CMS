package Model.service;

import Model.BucketDAO;
import Model.ServiceImpl;
import Model.entity.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BucketService extends ServiceImpl<Bucket, Integer, BucketDAO> {
	
	@Autowired
	private BucketDAO bct;
	
/*	@Transactional
	public void addGood(Bucket b, GoodItem g){
		b.addGood(g);
		bct.update(b);
	}
	@Transactional
	public void deleteGood(Bucket b, GoodItem g){
		b.deleteGood(g);
		bct.update(b);
	}
	
	@Transactional
	public List<Bucket> listBuckets(){
		return bct.getAll();
	}
	
	@Transactional
	public Bucket getById(Integer id){
		return bct.getById(id, false);
	}
	@Override
	@Transactional
	public void add(Bucket f) {
		// TODO Auto-generated method stub
		
	}
	@Override
	@Transactional
	public List<Bucket> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transactional
	public void deleteById(Integer f) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@Transactional
	public void update(Bucket video) {
		// TODO Auto-generated method stub
		
	}
	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}*/
	

}
