package Model;

import java.io.IOException;

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


public class AuthorizeFilter implements Filter {
	

	private UserService userService;


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	
	
	public boolean isAuthorized(UserDetails userdet){
		boolean isAuthorized = false;
		for(GrantedAuthority role: userdet.getAuthorities()){
			if(role.getAuthority().equals("ROLE_ANONYMOUS")){
				if(userdet.getUsername().equals("guest")){
					return false;
				}
				
			}
		}
	return true;
		
	}
	
	public User authorizeUser(){
		
		Object principal = null;
		principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username;
		UserDetails userdet;
		User user = null;
		boolean isAuthorized;
			if (principal instanceof UserDetails) {
				userdet = (UserDetails)principal;
				isAuthorized = isAuthorized(userdet);
	
				
				  username = (userdet).getUsername();
				  
				  if(isAuthorized){
					 
					  user = userService.getUsersByEmail(username).get(0);
					  
				  }
				  
				} else {
				 username = principal.toString();
				 isAuthorized = false;
				}

			return user;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		userDAO = new UserDAOImpl();
/*		 ServletContext servletContext = filterConfig.getServletContext();
		    WebApplicationContext webApplicationContext = 
		            WebApplicationContextUtils.getWebApplicationContext(servletContext);

		    AutowireCapableBeanFactory autowireCapableBeanFactory =
		           webApplicationContext.getAutowireCapableBeanFactory();

		    autowireCapableBeanFactory.configureBean(this, "userDAOImpl");*/
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		User user = authorizeUser();
	//	System.out.println("authorizefilter");
		if(user!=null){
			System.out.println("user is authorized");
			request.setAttribute("user", user);
			
			
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}