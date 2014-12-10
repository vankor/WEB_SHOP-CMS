package Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
@Service
public class RoleService extends ServiceImpl<Role, String, RoleDAO>{

		@Autowired
		private RoleDAO roleDAO;
		
/*		public void add(Role g){
			roleDAO.addRole(g);
			
		}
		public void delete(Role g){
			roleDAO.deleteRole(g);
			
		}
		public void deleteById(String id){
			roleDAO.deleteRoleById(id);
		}
		public List<Role> getAll(){
			return roleDAO.getAllRoles();
		}*/
		/*
		public Role getRoleById(String id){
			return roleDAO.getRoleById(id);
			
		}
		
		
		public void update(Role g){
			roleDAO.update(g);
			
		}
		
		@Override
		@Transactional
		public Integer getLastId() {
			// TODO Auto-generated method stub
			return null;
		}
	/*	@Override
		@Transactional
		public void deleteById(Integer f) {
			// TODO Auto-generated method stub
			
		}
		@Override
		@Transactional
		public Role getById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}*/

	}


