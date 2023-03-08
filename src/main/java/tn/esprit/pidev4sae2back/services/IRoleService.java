package tn.esprit.pidev4sae2back.services;

import java.util.List;

import tn.esprit.pidev4sae2back.entities.Role;

public interface IRoleService {

	
	List<Role> findAll();

	Role addRole(Role role);

	Role updateRole(Role role, Long roleID);

	void deleteRoleById(Long roleID);
}
