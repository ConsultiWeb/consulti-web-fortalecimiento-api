package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.Post;
import com.consulti.apiconsulti.model.PostReqDTO;
import com.consulti.apiconsulti.service.PostService;
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
@RequestMapping({ ConsultiConstants.POST_URL })
public class PostController {
  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  PostService userService;

  @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<Post> listar() throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POST_URL, ConsultiConstants.LISTA);
    GenericListResponse<Post> response = new GenericListResponse<>();
    response.setData(userService.listar());
    return response;
  }

  @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<Post> listarPor(@RequestBody Post request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POST_URL, ConsultiConstants.LISTA_POR);
    GenericListResponse<Post> response = new GenericListResponse<>();
    response.setData(userService.listarPor(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Post> guardar(@RequestBody PostReqDTO request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POST_URL, ConsultiConstants.GUARDAR);
    GenericBasicResponse<Post> response = new GenericBasicResponse<>();
    response.setData(userService.guardar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Post> actualizar(@RequestBody PostReqDTO request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POST_URL, ConsultiConstants.ACTUALIZAR);
    GenericBasicResponse<Post> response = new GenericBasicResponse<>();
    response.setData(userService.actualizar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> eliminar(@RequestBody Post request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POST_URL, ConsultiConstants.ELIMINAR);
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(userService.eliminar(request));
    return response;
  }
}