package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="operations")
class UserAuthority implements GrantedAuthority {
    
    private String authority;
    

   

	@Id
    @Column(name = "authority_id")
    public String getAuthority() {
        return authority;
    }
	
	 public void setAuthority(String authority) {
			this.authority = authority;
		}
	 

}
