package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.Label;
import com.consulti.apiconsulti.service.LabelService;
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
@RequestMapping({ ConsultiConstants.LABEL_URL })
public class LabelController {
  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  LabelService userService;

  @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<Label> listar() throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.LABEL_URL, ConsultiConstants.LISTA);
    GenericListResponse<Label> response = new GenericListResponse<>();
    response.setData(userService.listar());
    return response;
  }

  @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<Label> listarPor(@RequestBody Label request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.LABEL_URL, ConsultiConstants.LISTA_POR);
    GenericListResponse<Label> response = new GenericListResponse<>();
    response.setData(userService.listarPor(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Label> guardar(@RequestBody Label request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.LABEL_URL, ConsultiConstants.GUARDAR);
    GenericBasicResponse<Label> response = new GenericBasicResponse<>();
    response.setData(userService.guardar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Label> actualizar(@RequestBody Label request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.LABEL_URL, ConsultiConstants.ACTUALIZAR);
    GenericBasicResponse<Label> response = new GenericBasicResponse<>();
    response.setData(userService.actualizar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> eliminar(@RequestBody Label request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.LABEL_URL, ConsultiConstants.ELIMINAR);
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(userService.eliminar(request));
    return response;
  }
}
