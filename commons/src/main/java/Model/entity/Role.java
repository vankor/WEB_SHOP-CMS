package Model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "roles")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@authority")
public
class Role implements GrantedAuthority, Comparable, Model.entity.Entity {
    
	 private String authority;
	 private String description;
	      		
	 private Boolean isdeleted = false;
		
		
		@Column(name = "isdeleted")
		@Type(type = "boolean")
		public Boolean getIsdeleted() {
			return isdeleted;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((authority == null) ? 0 : authority.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Role other = (Role) obj;
			if (authority == null) {
				if (other.authority != null)
					return false;
			} else if (!authority.equals(other.authority))
				return false;
			return true;
		}
		public void setIsdeleted(Boolean isdeleted) {
			this.isdeleted = isdeleted;
		}

		@Override
		public String toString() {
			return authority;
		}
		@Id
	    @Column(name = "authority_id")
	    public String getAuthority() {
	        return authority;
	    }
		
		 public void setAuthority(String authority) {
				this.authority = authority;
			}
		 
		 @Column(name = "description")
		 public String getDescription() {
				return description;
			}

		public void setDescription(String description) {
				this.description = description;
			}

		@Override
		public Integer getId() {

			return null;
		}

		@Override
		public void setId(Integer id) {

			
		}

		@Override
		public int compareTo(Object o) {

			return 0;
		}
}