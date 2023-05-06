package org.vm93.registroelettronico.auth.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vm93.registroelettronico.auth.entity.User;
import org.vm93.registroelettronico.auth.repository.UserRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class AdminService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired @Qualifier("adminGenerator") private ObjectProvider <User> adminGenerator;
	
	public void salvaAdmin() {
		User admin = adminGenerator.getObject();
		if (repo.existsByUsername(admin.getUsername())) {
			throw new EntityExistsException("L'admin user esiste gia");
		}
		repo.save(admin);
	}
}
