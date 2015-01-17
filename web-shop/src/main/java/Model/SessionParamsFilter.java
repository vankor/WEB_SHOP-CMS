package Model;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionParamsFilter implements Filter {
		
	private TownService twnService;
	
	private PageService pageService;
	
	private PageGroupService pgrService;
	
	private BasicConfigurationService bcfService;
	
	private CategoryService catService;

	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		
	}

	public TownService getTwnService() {
		return twnService;
	}

	public void setTwnService(TownService twnService) {
		this.twnService = twnService;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public PageGroupService getPgrService() {
		return pgrService;
	}

	public void setPgrService(PageGroupService pgrService) {
		this.pgrService = pgrService;
	}

	public BasicConfigurationService getBcfService() {
		return bcfService;
	}

	public void setBcfService(BasicConfigurationService bcfService) {
		this.bcfService = bcfService;
	}

	public CategoryService getCatService() {
		return catService;
	}

	public void setCatService(CategoryService catService) {
		this.catService = catService;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
		String fullpath = ((HttpServletRequest) request).getRequestURI();
		String path = fullpath.split(request.getContextPath())[1];
		System.out.println("gpath "+path);
		if (path.contains("/resources/") ||path.equals("/") ||  request.getMethod().equals("POST") || path.matches("([^\\s]+(\\.(?i)(js|css|jpg|png|gif|bmp))$)") || path.startsWith("/index") || path.startsWith("/install")) {
		    chain.doFilter(request, response); 
		} else {
        String url = request.getServletPath();
		HttpSession sess = request.getSession(false);
		User user = (User)sess.getAttribute("user");
		
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
		if(bucket==null){bucket = new AnonimBuck();}
		sess.setAttribute("bucketsize", bucket.getSize());
				
		Set<Page> headerpages = pageService.getHeaderPages();
		sess.setAttribute("headerpages", headerpages);
		
		Set<PageGroup> pagegroups = pgrService.getFooterPagegroups();
		sess.setAttribute("pagegroups", pagegroups);
		
		Integer currenttownid = (Integer) sess.getAttribute("cityid");
		Town currenttown = new Town();
		if(currenttownid==null){
			if(user!=null){
			Town usrtown = user.getTown();
			if(usrtown!=null){
			currenttown = usrtown;
			currenttownid = usrtown.getId();
			}
			}
			else{
				currenttown = twnService.getDefault();
			}
		}
		else
		currenttown = twnService.getById(currenttownid);
		sess.setAttribute("currenttown", currenttown);
		
		List<BasicConfiguration> bcfgs = bcfService.getAll();
		BasicConfiguration basic = bcfgs.get(0);
		sess.setAttribute("basic", basic);
		
		List<Category> roots = catService.getRootCategories();
		sess.setAttribute("currentCatList", roots);
		
		chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}

