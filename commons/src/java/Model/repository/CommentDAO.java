package Model.repository;

import Model.entity.Comment;
import Model.entity.GoodItem;

import java.util.Set;


public interface CommentDAO extends GenericDAO<Comment, Integer>{
/*	public void addComment(Comment g);
	public void deleteComment(Comment g);
	public void deleteCommentById(int id);
	public List<Object> getAllComments();
	public Comment getCommentById(int id);*/
	public Set<Comment> getAnswers(Comment com, Boolean onlyproved);
	public Set<Comment> getAnswersById(Integer id, Boolean onlyproved);
	public Set<Comment> getProvedComments();
	public Set<Comment> getProvedCommentsByGood(GoodItem good);
	public Integer save(Comment entity);
//	public List<Object> getFiltered(Filter n);
/*	public void update(Comment k);*/
}
