package Model;

import java.util.ArrayList;
import java.util.List;

public class VoteBean {
	
	private List<Vote> votes = new ArrayList<Vote>();

	public List<Vote> getvotes() {
		return votes;
	}

	public void setvotes(List<Vote> votes) {
		this.votes = votes;
	}

}
