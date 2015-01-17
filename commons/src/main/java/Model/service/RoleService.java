package Model.service;

import Model.repository.RoleDAO;
import Model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoleService extends ServiceImpl<Role, String, RoleDAO> {

		@Autowired
		private RoleDAO roleDAO;
		



	}


