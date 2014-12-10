package Model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface PageDAO extends GenericDAO<Page, Integer>{
	public Set<Page> getinfopages();
	public Set<Page> listInfoPagesPage(Filter goodfilter, Integer begin, Integer end);
	public Set<Page> getHeaderPages();
	public Page getPageByUrl(String url);

}
