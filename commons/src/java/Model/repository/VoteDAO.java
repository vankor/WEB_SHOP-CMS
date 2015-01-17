package Model.repository;

import Model.entity.Vote;
import Model.repository.GenericDAO;

import java.util.List;

public interface VoteDAO extends GenericDAO<Vote, Integer> {
	
	public void addVote(Vote g);
	public void deleteVote(Vote g);
	public void deleteVoteById(Integer id);
	public List<Vote> getAllVotes();
	public Vote getVoteById(Integer id);
	public void update(Vote g);
	public Vote getVoteByName(String name);

}