package tn.esprit.pidev4sae2back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import lombok.extern.slf4j.Slf4j;
import tn.esprit.pidev4sae2back.entities.Role;
import tn.esprit.pidev4sae2back.repositories.RoleRepository;


@Service
@Slf4j
public class RoleServiceImpl implements IRoleService {

	
	@Autowired
	RoleRepository roleRepository;
	
	
	
	//afficher la liste des users
	@Override
	public List<Role> findAll() {
		return  roleRepository.findAll();
	}




	//ajouter un user
	@Override
	public Role addRole(Role role) {
		
	return roleRepository.save(role);
	}
	
	@Override
	public Role updateRole(Role role, Long roleID) {
		
			role.setRoleId(roleID);
			return roleRepository.save(role);
		
	}
	
	//effacer un user
	@Override
	public void deleteRoleById(Long roleID) {
		roleRepository.deleteById(roleID);
		
	}
	
	
}
