package org.vm93.registroelettronico.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vm93.registroelettronico.auth.entity.User;
import org.vm93.registroelettronico.auth.payload.JWTAuthResponse;
import org.vm93.registroelettronico.auth.payload.LoginDto;
import org.vm93.registroelettronico.auth.payload.RegisterDto;
import org.vm93.registroelettronico.auth.repository.UserRepository;
import org.vm93.registroelettronico.auth.service.AuthService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private AuthService authService;
  
  @Autowired UserRepository userRepo;

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

  // Build Register REST API
  @PostMapping(value = { "/register", "/signup" })
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
    String response = authService.register(registerDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
