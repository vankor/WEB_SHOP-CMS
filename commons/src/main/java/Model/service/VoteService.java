package Model.service;

import java.util.List;

import Model.repository.VoteDAO;
import Model.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteService extends ServiceImpl<Vote, Integer, VoteDAO> {
	@Autowired
	private VoteDAO voteDAO;
	
	@Override
	@Transactional
	public void add(Vote g){
		voteDAO.addVote(g);
		
	}
	
	@Transactional
	public void delete(Vote g){
		voteDAO.deleteVote(g);
		
	}
	@Override
	@Transactional
	public void deleteById(Integer id){
		voteDAO.deleteVoteById(id);
	}
	
	@Override
	@Transactional
	public List<Vote> getAll(){
		return voteDAO.getAllVotes();
	}
	
	@Override
	@Transactional
	public Vote getById(Integer id){
		return voteDAO.getVoteById(id);
		
	}
	
	
	@Transactional
	public Vote getByName(String name){
		return voteDAO.getVoteByName(name);
		
	}
	
	@Override
	@Transactional
	public void update(Vote g){
		voteDAO.update(g);
		
	}
	
	@Override
	@Transactional
	public Integer getLastId() {

		return null;
	}

}
