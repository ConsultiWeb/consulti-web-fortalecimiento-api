package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.DetalleResume;
import com.consulti.apiconsulti.service.DetalleResumeService;
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
@RequestMapping({ ConsultiConstants.DETALLE_RESUME_URL })
public class DetalleController {

  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  DetalleResumeService detalleService;

  @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<DetalleResume> listar() throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.DETALLE_RESUME_URL, ConsultiConstants.LISTA);
    GenericListResponse<DetalleResume> response = new GenericListResponse<>();
    response.setData(detalleService.listar());
    return response;
  }

  @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<DetalleResume> listarPor(@RequestBody DetalleResume request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.DETALLE_RESUME_URL, ConsultiConstants.LISTA_POR);
    GenericListResponse<DetalleResume> response = new GenericListResponse<>();
    response.setData(detalleService.listarPor(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<DetalleResume> guardar(@RequestBody DetalleResume request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.DETALLE_RESUME_URL, ConsultiConstants.GUARDAR);
    GenericBasicResponse<DetalleResume> response = new GenericBasicResponse<>();
    response.setData(detalleService.guardar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<DetalleResume> actualizar(@RequestBody DetalleResume request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.DETALLE_RESUME_URL, ConsultiConstants.ACTUALIZAR);
    GenericBasicResponse<DetalleResume> response = new GenericBasicResponse<>();
    response.setData(detalleService.actualizar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> eliminar(@RequestBody DetalleResume request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.DETALLE_RESUME_URL, ConsultiConstants.ELIMINAR);
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(detalleService.eliminar(request));
    return response;
  }
}
