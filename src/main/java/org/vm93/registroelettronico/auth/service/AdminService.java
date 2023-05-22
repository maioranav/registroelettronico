package org.vm93.registroelettronico.auth.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.auth.entity.ERole;
import org.vm93.registroelettronico.auth.entity.Role;
import org.vm93.registroelettronico.auth.entity.User;
import org.vm93.registroelettronico.auth.repository.RoleRepository;
import org.vm93.registroelettronico.auth.repository.UserRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class AdminService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	RoleRepository rolesRepo;
	
	@Autowired @Qualifier("adminGenerator") private ObjectProvider <User> adminGenerator;
	
	public User salvaAdmin() {
		if (!rolesRepo.existsByRoleName(ERole.ROLE_ADMIN) && !rolesRepo.existsByRoleName(ERole.ROLE_USER))
		{Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		rolesRepo.save(admin);

		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		rolesRepo.save(user);
		rolesRepo.save(admin);}
		
		User a = adminGenerator.getObject();
		if (repo.existsByUsername(a.getUsername())) {
			throw new EntityExistsException("L'admin user esiste gia");
		}
		
		repo.save(a);
		return a;
	}
}
