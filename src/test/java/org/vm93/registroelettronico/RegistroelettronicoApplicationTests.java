package org.vm93.registroelettronico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vm93.registroelettronico.auth.entity.ERole;
import org.vm93.registroelettronico.auth.entity.Role;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.model.Plesso;

@SpringBootTest
class RegistroelettronicoApplicationTests {

	Corso c = new Corso();
	Plesso p = Plesso.builder().id(1l).name("Plesso Test").build();
	Docente d = new Docente();
	PasswordEncoder pe = new BCryptPasswordEncoder();
	String passenc = pe.encode("notencrypted");
	
	@BeforeEach
	void beforeTests(){
		c.setPlesso(p);
		d.setEmail("test@mail.com");
		d.setUsername("testuser");
		d.setPassword(passenc);
		d.setName("Utente Test");
		d.setSurname("Cognome Test");
		Set<Role> ruolo = new HashSet<>();
		ruolo.add(new Role(1l, ERole.ROLE_USER));
		d.setRoles(ruolo);
		c.setDocente(d);
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testPlesso() {
		assertNotNull(c.getPlesso());
		assertNotNull(c.getPlesso().getName());
		assertEquals(Long.parseLong(c.getPlesso().getId().toString()), 1l);
	}
	
	@Test
	void testDocente() {
		assertEquals(c.getDocente().getUsername(), "testuser");
		assertNotNull(c.getDocente().getRoles());
		assertTrue(c.getDocente().getPassword().equals(passenc));
		assertEquals(d.getClass().getSimpleName(), "Docente");
	}

}
