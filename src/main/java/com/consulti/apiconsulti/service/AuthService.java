package com.consulti.apiconsulti.service;

import javax.servlet.http.HttpServletRequest;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.model.JwtRequest;
import com.consulti.apiconsulti.model.JwtResponse;
import com.consulti.apiconsulti.model.PasswordReqDTO;
import com.consulti.apiconsulti.model.TokenDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public interface AuthService {
  public JwtResponse auth(JwtRequest request) throws GenericException;
  public JwtResponse socialAuth(String email) throws GenericException;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

  public Boolean validateTokenPassword(String request);

  public User confirmPassword(PasswordReqDTO request) throws GenericException;

  public Boolean resetearContrasena(HttpServletRequest request, String correo) throws UsernameNotFoundException;

  public String authFacebook(TokenDto tokenDto) throws GenericException;
  public String authGoogle(TokenDto tokenDto) throws IOException;
  public String authLinkedin(TokenDto tokenDto) throws IOException;
}
