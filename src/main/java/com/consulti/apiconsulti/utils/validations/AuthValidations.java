package com.consulti.apiconsulti.utils.validations;

import java.util.Calendar;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.ResetToken;
import com.consulti.apiconsulti.repository.ResetTokenRespository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthValidations {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ResetTokenRespository requestTokenRepo;

  public void authenticate(String username, String password) throws GenericException {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new GenericException("Usuario deshabilitado", CoreUtilConstants.EXISTING_VALUES);
    } catch (BadCredentialsException e) {
      throw new GenericException("Credenciales Inválidas", CoreUtilConstants.INFORMATIVE_VALUES);
    }
  }

  public Boolean validatePasswordResetToken(String token) {
    final ResetToken passToken = requestTokenRepo.findByToken(token);
    return !isTokenFound(passToken) ? false : isTokenExpired(passToken) ? false : true;
  }

  private boolean isTokenFound(ResetToken passToken) {
    return passToken != null;
  }

  private boolean isTokenExpired(ResetToken passToken) {
    final Calendar cal = Calendar.getInstance();
    return passToken.getFechaExpiracion().before(cal.getTime());
  }
}