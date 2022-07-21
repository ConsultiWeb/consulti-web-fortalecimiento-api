package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.model.MailReqDTO;
import com.consulti.apiconsulti.service.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  MailService mailService;

  @PostMapping(path = "/sendMail", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> enviarMail(@RequestBody MailReqDTO request) throws Exception {
    log.info("Enviando correo");
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    request.setDe("no-reply@consulti.ec");
    response.setData(mailService.sendSimpleMessage(request));
    return response;
  }

  @PostMapping(path = "/sendMailWithFile", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> enviarMailArchivo(@RequestBody MailReqDTO request) throws Exception {
    log.info("Enviando correo");
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    request.setDe("no-reply@consulti.ec");
    response.setData(mailService.sendMessageWithAttachment(request));
    return response;
  }
}
