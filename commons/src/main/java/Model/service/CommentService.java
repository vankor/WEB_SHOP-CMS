package Model.service;

import java.util.Set;

import Model.repository.CommentDAO;
import Model.entity.Comment;
import Model.entity.GoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService extends ServiceImpl<Comment, Integer, CommentDAO> {

	@Autowired
	private CommentDAO cmt;
	

	
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


}
