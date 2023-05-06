package org.vm93.registroelettronico.auth.service;

import org.vm93.registroelettronico.auth.payload.LoginDto;
import org.vm93.registroelettronico.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
