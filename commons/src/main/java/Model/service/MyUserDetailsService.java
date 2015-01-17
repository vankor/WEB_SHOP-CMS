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
				
















			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
				return us;
			
		}


}


