package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.service.UserService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping({ ConsultiConstants.USER_URL })
public class UserController {
  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  UserService userService;

  @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<User> listar() throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.USER_URL, ConsultiConstants.LISTA);
    GenericListResponse<User> response = new GenericListResponse<>();
    response.setData(userService.listar());
    return response;
  }

  @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<User> listarPor(@RequestBody User request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.USER_URL, ConsultiConstants.LISTA_POR);
    GenericListResponse<User> response = new GenericListResponse<>();
    response.setData(userService.listarPor(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<User> actualizar(@RequestBody User request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.USER_URL, ConsultiConstants.ACTUALIZAR);
    GenericBasicResponse<User> response = new GenericBasicResponse<>();
    response.setData(userService.actualizar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> eliminar(@RequestBody User request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.USER_URL, ConsultiConstants.ELIMINAR);
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(userService.eliminar(request));
    return response;
  }
}
