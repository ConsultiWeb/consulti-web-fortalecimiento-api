package com.consulti.apiconsulti.controller;

import javax.servlet.http.HttpServletRequest;
import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.entity.InformacionContacto;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.model.*;
import com.consulti.apiconsulti.service.AuthService;
import com.consulti.apiconsulti.service.InformacionContactoService;
import com.consulti.apiconsulti.service.UserService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AuthController {
  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  AuthService authService;

  @Autowired
  UserService userService;

  @Autowired
  InformacionContactoService informacionContactoService;

  @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
    GenericBasicResponse<JwtResponse> response = new GenericBasicResponse<>();
    response.setData(authService.auth(request));
    return response;
  }
  @PostMapping("/google")
  public GenericBasicResponse<JwtResponse> google(@RequestBody TokenDto tokenDto) throws Exception {
    GenericBasicResponse<JwtResponse> response = new GenericBasicResponse<>();
    response.setData(authService.socialAuth(authService.authGoogle(tokenDto)));
    return response;
  }
  @PostMapping("/facebook")
  public GenericBasicResponse<JwtResponse> facebook(@RequestBody TokenDto tokenDto) throws Exception {
    GenericBasicResponse<JwtResponse> response = new GenericBasicResponse<>();
    response.setData(authService.socialAuth(authService.authFacebook(tokenDto)));
    return response;
  }
  @PostMapping("/linkedin")
  public GenericBasicResponse<JwtResponse>linkedin(@RequestBody TokenDto tokenDto) throws Exception {
    GenericBasicResponse<JwtResponse> response = new GenericBasicResponse<>();
    response.setData(authService.socialAuth(authService.authLinkedin(tokenDto)));
    return response;
  }
  @PostMapping(path = { ConsultiConstants.REGISTER }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<User> actualizar(@RequestBody User request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.USER_URL, ConsultiConstants.REGISTER);
    GenericBasicResponse<User> response = new GenericBasicResponse<>();
    response.setData(userService.guardar(request));
    return response;
  }
  @PostMapping(path = { "/actualizar_contacto" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<InformacionContacto> actualizarContacto(@RequestBody InformacionContactoDTO contacto) throws Exception {
    GenericBasicResponse<InformacionContacto> response = new GenericBasicResponse<>();
    List <InformacionContacto> conct = contacto.getContacto();
    informacionContactoService.eliminar(contacto.getUser());
    for (InformacionContacto element : conct) {
      if(element.getId()==null){
        informacionContactoService.guardar(element);
      }else{
        element.setEstado("Activo");
        informacionContactoService.actualizar(element);

      }
    }
    return response;
  }
  @PostMapping(path = "/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> resetearContrasena(HttpServletRequest request,
      @RequestParam("email") String mail) throws Exception {
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(authService.resetearContrasena(request, mail));
    return response;
  }

  @PostMapping(path = "/validatePasswordToken", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> resetearContrasena(@RequestParam("token") String token) throws Exception {
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(authService.validateTokenPassword(token));
    return response;
  }

  @PostMapping(path = "/confirmPassword", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<User> confirmPassword(@RequestBody PasswordReqDTO request) throws Exception {
    GenericBasicResponse<User> response = new GenericBasicResponse<>();
    response.setData(authService.confirmPassword(request));
    return response;
  }
}
