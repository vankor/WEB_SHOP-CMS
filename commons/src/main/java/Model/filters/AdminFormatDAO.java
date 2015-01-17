package Model.filters;

import java.util.List;

import Model.repository.GenericDAO;


public interface AdminFormatDAO extends GenericDAO<AdminFormat, Integer> {
	List<AdminFormat> getClassFormats(String adminclasssname);
}
