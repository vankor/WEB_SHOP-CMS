package Model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class RegistrationBean {
	
		private String email;
		private String name;
		private String pass; 
		private Town city;
		private Boolean mailer;
		
		
		@NotEmpty(message = "*Необходимо заполнить логин!")
		@NotNull(message = "*Необходимо заполнить логин!")
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		@NotEmpty(message = "*Необходимо заполнить имя!")
		@NotNull(message = "*Необходимо заполнить имя!")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@NotEmpty(message = "*Необходимо заполнить пароль!")
		@NotNull(message = "*Необходимо заполнить пароль!")
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		
		public Town getCity() {
			return city;
		}
		public void setCity(Town city) {
			this.city = city;
		}
		public Boolean getMailer() {
			return mailer;
		}
		public void setMailer(Boolean mailer) {
			this.mailer = mailer;
		}
		
		
}
