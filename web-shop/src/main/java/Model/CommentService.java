package Model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService extends ServiceImpl<Comment, Integer, CommentDAO>{

	@Autowired
	private CommentDAO cmt;
	
/*	@Transactional
	public void delete(Comment y){
		cmt.delete(y);
	}
	
	@Transactional
	public void deleteById(int id){
		cmt.deleteById(id);
	}*/
	
	@Transactional
	public Set<Comment> getAnswersById(Integer id, Boolean onlyproved) {
		return cmt.getAnswersById(id, onlyproved);}
	
	@Transactional
	public Set<Comment> getAnswers(Comment com, Boolean onlyproved) {
		return cmt.getAnswers(com, onlyproved);
	}
	@Transactional
	public Integer save(Comment entity){
		return cmt.save(entity);
	}
	@Transactional
	public Set<Comment> getProvedComments(){
		return cmt.getProvedComments();
	}
	
	@Transactional
	public Set<Comment> getProvedCommentsByGood(GoodItem good) {
		return cmt.getProvedCommentsByGood(good);
		
	}

/*
	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void add(Comment f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Collection<Comment> getAll() {
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
	public Comment getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void update(Comment video) {
		// TODO Auto-generated method stub
		
	}
	
*/
}
