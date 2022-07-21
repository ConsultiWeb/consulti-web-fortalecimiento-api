package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.Postulacion;
import com.consulti.apiconsulti.model.MailPostulacionReqDTO;
import com.consulti.apiconsulti.model.PostulacionProcesoReclutamientoDTO;
import com.consulti.apiconsulti.model.PostulacionReqDTO;
import com.consulti.apiconsulti.service.PostulacionService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({ ConsultiConstants.POSTULACION_URL })
public class PostulacionController {

  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  PostulacionService postulacionService;

  @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<Postulacion> listar() throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, ConsultiConstants.LISTA);
    GenericListResponse<Postulacion> response = new GenericListResponse<>();
    response.setData(postulacionService.listar());
    return response;
  }

  @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<Postulacion> listarPor(@RequestBody Postulacion request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, ConsultiConstants.LISTA_POR);
    GenericListResponse<Postulacion> response = new GenericListResponse<>();
    response.setData(postulacionService.listarPor(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Postulacion> guardar(@RequestBody PostulacionReqDTO request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, ConsultiConstants.GUARDAR);
    GenericBasicResponse<Postulacion> response = new GenericBasicResponse<>();
    response.setData(postulacionService.guardar(request));
    return response;
  }
  @PostMapping(path = { ConsultiConstants.POSTULAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Postulacion> postular(@RequestBody PostulacionProcesoReclutamientoDTO request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, ConsultiConstants.GUARDAR);
    GenericBasicResponse<Postulacion> response = new GenericBasicResponse<>();
    response.setData(postulacionService.postular(request));
    return response;
  }
  @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Postulacion> actualizar(@RequestBody PostulacionReqDTO request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, ConsultiConstants.ACTUALIZAR);
    GenericBasicResponse<Postulacion> response = new GenericBasicResponse<>();
    response.setData(postulacionService.actualizar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> eliminar(@RequestBody Postulacion request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, ConsultiConstants.ELIMINAR);
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(postulacionService.eliminar(request));
    return response;
  }

  @PostMapping(path = "/enviarInvitacionT", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE, })
  public GenericBasicResponse<Boolean> enviarInvitacionT(@RequestParam("file") MultipartFile file,
      @RequestParam("request") String request) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, "enviarInvitacionT");
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    MailPostulacionReqDTO parsedReq = objectMapper.readValue(request, MailPostulacionReqDTO.class);
    response.setData(postulacionService.enviarInvitacionTeorico(parsedReq, file));
    return response;
  }

  @PostMapping(path = "/enviarHojaDeVida", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE, })
  public GenericBasicResponse<Boolean> enviarHojaDeVida(@RequestParam("file") MultipartFile file,
      @RequestParam("request") String request) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, "enviarHojaDeVida");
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    MailPostulacionReqDTO parsedReq = objectMapper.readValue(request, MailPostulacionReqDTO.class);
    response.setData(postulacionService.enviarHojaDeVida(parsedReq, file));
    return response;
  }

  @PostMapping(path = "/enviarMultiHojaDeVida", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE, })
  public GenericBasicResponse<Boolean> enviarMultiHojaDeVida(@RequestParam("file") MultipartFile[] file,
      @RequestParam("request") String request) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.POSTULACION_URL, "enviarMultiHojaDeVida");
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    MailPostulacionReqDTO parsedReq = objectMapper.readValue(request, MailPostulacionReqDTO.class);
    response.setData(postulacionService.enviarMultipleHojaDeVida(parsedReq, file));
    return response;
  }
}
