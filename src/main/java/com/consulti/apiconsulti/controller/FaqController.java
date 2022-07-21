package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.Faq;
import com.consulti.apiconsulti.service.FaqService;
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
@RequestMapping({ ConsultiConstants.FAQ_URL })
public class FaqController {

  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  FaqService faqService;

  @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<Faq> listar() throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FAQ_URL, ConsultiConstants.LISTA);
    GenericListResponse<Faq> response = new GenericListResponse<>();
    response.setData(faqService.listar());
    return response;
  }

  @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<Faq> listarPor(@RequestBody Faq request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FAQ_URL, ConsultiConstants.LISTA_POR);
    GenericListResponse<Faq> response = new GenericListResponse<>();
    response.setData(faqService.listarPor(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Faq> guardar(@RequestBody Faq request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FAQ_URL, ConsultiConstants.GUARDAR);
    GenericBasicResponse<Faq> response = new GenericBasicResponse<>();
    response.setData(faqService.guardar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Faq> actualizar(@RequestBody Faq request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FAQ_URL, ConsultiConstants.ACTUALIZAR);
    GenericBasicResponse<Faq> response = new GenericBasicResponse<>();
    response.setData(faqService.actualizar(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> eliminar(@RequestBody Faq request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FAQ_URL, ConsultiConstants.ELIMINAR);
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(faqService.eliminar(request));
    return response;
  }
}
