package org.vm93.registroelettronico.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vm93.registroelettronico.auth.entity.User;
import org.vm93.registroelettronico.auth.payload.JWTAuthResponse;
import org.vm93.registroelettronico.auth.payload.LoginDto;
import org.vm93.registroelettronico.auth.payload.RegisterDto;
import org.vm93.registroelettronico.auth.repository.UserRepository;
import org.vm93.registroelettronico.auth.service.AdminService;
import org.vm93.registroelettronico.auth.service.AuthService;
import org.vm93.registroelettronico.model.Docente;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@CrossOrigin(origins = "*", maxAge = 360000)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AdminService adminService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	// Build Login REST API
	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
		String token = authService.login(loginDto);
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		User u = userRepo.findByUsername(loginDto.getUsername()).get();
		jwtAuthResponse.setUsername(loginDto.getUsername());
		jwtAuthResponse.setAccessToken(token);
		jwtAuthResponse.setRole(u.getRoles());
		jwtAuthResponse.setUserType(u.getClass().getSimpleName());

		return ResponseEntity.ok(jwtAuthResponse);
	}

	@GetMapping("/profile")
	public ResponseEntity<?> getProfile() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User usercomplete = userRepo.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(usercomplete, HttpStatus.OK);
	}
	
	@GetMapping("/firstboot")
	public ResponseEntity<?> firstBoot() {
		return new ResponseEntity<>(adminService.salvaAdmin(), HttpStatus.CREATED);
	}

}
