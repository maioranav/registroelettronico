package org.vm93.registroelettronico.auth.configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vm93.registroelettronico.auth.entity.ERole;
import org.vm93.registroelettronico.auth.entity.Role;
import org.vm93.registroelettronico.auth.entity.User;
import org.vm93.registroelettronico.auth.repository.RoleRepository;
import org.vm93.registroelettronico.auth.repository.UserRepository;

@Configuration
public class AdminConfig {

	@Autowired
	UserRepository repo;
	@Autowired
	RoleRepository rolesrepo;

	@Value("${user.admin.name}")
	private String name;
	@Value("${user.admin.surname}")
	private String surname;
	@Value("${user.admin.email}")
	private String email;
	@Value("${user.admin.username}")
	private String username;
	@Value("${user.admin.password}")
	private String password;

	@Bean
	@Scope("prototype")
	public User adminGenerator() {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		User u = new User();
		u.setName(name);
		u.setSurname(surname);
		u.setEmail(email);
		u.setUsername(username);
		u.setPassword(pe.encode(password));

		Set<Role> roles = new HashSet<>();

		Role adminRole = rolesrepo.findByRoleName(ERole.ROLE_ADMIN).get();
		roles.add(adminRole);

		u.setRoles(roles);

		return u;
	}

}
