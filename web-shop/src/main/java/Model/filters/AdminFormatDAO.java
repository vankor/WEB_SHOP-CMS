package Model.filters;

import java.util.List;

import Model.Bucket;
import Model.GenericDAO;


public interface AdminFormatDAO extends GenericDAO<AdminFormat, Integer> {
	List<AdminFormat> getClassFormats(String adminclasssname);
}
