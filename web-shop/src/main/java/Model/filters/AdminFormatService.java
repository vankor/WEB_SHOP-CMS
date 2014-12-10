package Model.filters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.GoodDAO;
import Model.ServiceImpl;
import Model.Vote;
import Model.VoteDAO;

@Service
public class AdminFormatService extends ServiceImpl<AdminFormat, Integer, AdminFormatDAO>{
	@Autowired
	private AdminFormatDAO adfDAO;
	public List<AdminFormat> getClassFormats(String adminclasssname) {
		return adfDAO.getClassFormats(adminclasssname);
	}
}
