package org.vm93.registroelettronico.runner;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.vm93.registroelettronico.auth.entity.ERole;
import org.vm93.registroelettronico.auth.entity.Role;
import org.vm93.registroelettronico.auth.entity.User;
import org.vm93.registroelettronico.auth.repository.RoleRepository;
import org.vm93.registroelettronico.auth.repository.UserRepository;
import org.vm93.registroelettronico.auth.service.AdminService;
import org.vm93.registroelettronico.auth.service.AuthService;
import org.vm93.registroelettronico.service.CorsoService;
import org.vm93.registroelettronico.service.DocenteService;
import org.vm93.registroelettronico.service.PlessoService;
import org.vm93.registroelettronico.service.StudenteService;

@Component
public class RegistroRunner implements ApplicationRunner {

	@Autowired	RoleRepository roleRepository;
	@Autowired	UserRepository userRepository;
	@Autowired	PasswordEncoder passwordEncoder;
	@Autowired	AuthService authService;
	@Autowired	AdminService adminService;
	private static Set<Role> adminRole;
	private static Set<Role> userRole;
	
	@Autowired DocenteService docenteService;
	@Autowired StudenteService studenteService;
	@Autowired PlessoService plessoService;
	@Autowired CorsoService corsoService;
	

	@Override 
	public void run(ApplicationArguments args) throws Exception {
		//setRoleDefault();
		//adminService.salvaAdmin();
		//docenteService.generaFakeDocente();
		//studenteService.generaFakeStudente();
		//plessoService.generaFakePlesso();
		//corsoService.generaFakeCorso();
		
	}

	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);

		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);

		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(user);

		userRole = new HashSet<Role>();
		userRole.add(user);
	}

}
