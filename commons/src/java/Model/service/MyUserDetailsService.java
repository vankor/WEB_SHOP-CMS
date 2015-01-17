package Model.service;

import java.util.List;

import Model.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

@Controller
public class MyUserDetailsService implements UserDetailsService {
	
		@Autowired
		private UserService usrServ;
	
		private static final Logger log = Logger
				.getLogger(MyUserDetailsService.class);

		public UserDetails loadUserByUsername(String login)
				 {
			User us = null;
			try{
				
		//		Appender fh;  
				
		//		 fh = new FileAppender(new SimpleLayout(), "D:/MyLogFile.log");  
		//		 	log.addAppender(fh);
		//		 	fh.setLayout(new SimpleLayout());
		//	         // the following statement is used to log any messages  
			        log.info("My first log");  
				
				
				log.debug("loadUserByUsername("+login+");");
				
				System.out.println("wefwfrfrew");
				
				List<User> users  = usrServ.getUsersByEmail(login);
				System.out.println("Найдено пользователей:"+users.size());
				us = users.get(0);
				if (us == null)
					throw new UsernameNotFoundException("Email " + login
							+ " doesn't exist!");
				System.out.println(us);	
				
				for(GrantedAuthority h: us.getAuthorities())
				{
					System.out.println(h.getAuthority());
				}
				
	//			String username = us.getUsername();
	//			String password = us.getPassword();
	//			boolean notLocked = !us.isAccountNonLocked();
	//			boolean notExpired = !us.isAccountExpired();
	//			boolean notCredExpired = !us.isCredentialsExpired();
	//			boolean enabled = notLocked && notExpired;
	//			GrantedAuthority auth = new GrantedAuthority() {
	//				private static final long serialVersionUID = 1L;
//
	//				public String getAuthority() {
	//					return "ROLE_USER";
	//				}
	//			};
	//			Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
	//			set.add(auth);
	//			us.setAuthorities(set);
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
				return us;
			
		}


}


