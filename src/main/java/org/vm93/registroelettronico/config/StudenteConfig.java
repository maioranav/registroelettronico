package org.vm93.registroelettronico.config;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vm93.registroelettronico.auth.entity.ERole;
import org.vm93.registroelettronico.auth.entity.Role;
import org.vm93.registroelettronico.auth.repository.RoleRepository;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.model.Studente;

import com.github.javafaker.Faker;

@Configuration
public class StudenteConfig {

	@Autowired
	RoleRepository rolesrepo;

	@Bean
	@Scope("prototype")
	public Studente fakeStudente() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		PasswordEncoder pe = new BCryptPasswordEncoder();
		Studente d = new Studente();
		d.setName(fake.name().firstName());
		d.setSurname(fake.name().lastName());
		String username = d.getName().toLowerCase().charAt(0) + "." + d.getSurname().toLowerCase();
		d.setUsername(username);
		d.setEmail(username + "@univincenzo.it");
		String password = pe.encode(fake.internet().password(8, 12, true, true));
		d.setPassword(password);
		Set<Role> roles = new HashSet<>();
		Role userRole = rolesrepo.findByRoleName(ERole.ROLE_USER).get();
		roles.add(userRole);
		d.setRoles(roles);
		return d;
	}
}
