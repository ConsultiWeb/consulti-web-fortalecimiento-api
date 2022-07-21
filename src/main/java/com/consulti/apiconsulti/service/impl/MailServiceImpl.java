package com.consulti.apiconsulti.service.impl;

import java.io.File;
import java.util.ArrayList;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.consulti.apiconsulti.model.MailReqDTO;
import com.consulti.apiconsulti.model.ResetTokenMailReqDTO;
import com.consulti.apiconsulti.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MailServiceImpl implements MailService {
  @Autowired
  private JavaMailSender emailSender;

  @Value("${hostFrontend}")
  String hostFrontend;

  @Value("${mail.de}")
  String noReplyMail;


  @Override
  @Async
  public Boolean sendRawMessage(String email) {
    Boolean response = false;
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(noReplyMail);
      message.setTo(email);
      message.setSubject("Confirmación de cuenta");
      message.setText("Su cuenta ha sido creada exitosamente. Pronto nos comunicaremos con usted.");
      emailSender.send(message);
      response = true;
    } catch (MailException exception) {
      exception.printStackTrace();
    }
    return response;
  }


  @Override
  @Async
  public Boolean sendSimpleMessage(MailReqDTO request) {
    Boolean response = false;
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(noReplyMail);
      message.setFrom(request.getDe() != null ? request.getDe() : noReplyMail);
      message.setTo(request.getPara());
      message.setSubject(request.getAsunto());
      message.setText(request.getTexto());
      emailSender.send(message);
      response = true;
    } catch (MailException exception) {
      exception.printStackTrace();
    }
    return response;
  }
  @Override
  @Async
  public Boolean sendWithCCOnly(String de, String para, ArrayList<String> correos) {
    Boolean response = false;
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(de != null ?de : noReplyMail);
      if (correos != null && !(correos.isEmpty())) {
        String listaCC = StringUtils.arrayToCommaDelimitedString(correos.toArray());
        helper.setCc(InternetAddress.parse(listaCC));
      }
      helper.setSubject("Registrate ahora!");
      helper.setText("Te invitamos a nuestro proceso de reclutamiento, para mayor información registrate en nuestra plataforma: https://consulti.ec/");
      emailSender.send(message);
      response = true;
    } catch (MailException exception) {
      exception.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return response;
  }
  @Override
  public Boolean sendMessageWithAttachment(MailReqDTO request) {
    Boolean response = false;
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(request.getDe() != null ? request.getDe() : noReplyMail);
      helper.setTo(request.getPara());
      if (request.getCc() != null && !request.getCc().isEmpty()) {
        String listaCC = StringUtils.arrayToCommaDelimitedString(request.getCc().toArray());
        helper.setCc(InternetAddress.parse(listaCC));
      }
      if (request.getBcc() != null && !request.getBcc().isEmpty()) {
        String listaBCC = StringUtils.arrayToCommaDelimitedString(request.getBcc().toArray());
        helper.setBcc(InternetAddress.parse(listaBCC));
      }
      helper.setSubject(request.getAsunto());
      helper.setText(request.getTexto());
      FileSystemResource fileSystemResource = new FileSystemResource(new File(request.getRutaArchivo()));
      String fileName = fileSystemResource.getFilename();
      helper.addAttachment(fileName, fileSystemResource);
      emailSender.send(message);
      response = true;
    } catch (MailException exception) {
      exception.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public Boolean sendMessageWithMultipleAttachments(MailReqDTO request) {
    Boolean response = false;
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(request.getDe() != null ? request.getDe() : noReplyMail);
      helper.setTo(request.getPara());
      helper.setSubject(request.getAsunto());
      helper.setText(request.getTexto());
      if (request.getCc() != null && !request.getCc().isEmpty()) {
        String listaCC = StringUtils.arrayToCommaDelimitedString(request.getCc().toArray());
        helper.setCc(InternetAddress.parse(listaCC));
      }
      if (request.getBcc() != null && !request.getBcc().isEmpty()) {
        String listaBCC = StringUtils.arrayToCommaDelimitedString(request.getBcc().toArray());
        helper.setBcc(InternetAddress.parse(listaBCC));
      }
      if (request.getRutasListaArchivos() != null && !request.getRutasListaArchivos().isEmpty()) {
        for (String rutaArchivo : request.getRutasListaArchivos()) {
          FileSystemResource fileSystemResource = new FileSystemResource(new File(rutaArchivo));
          String fileName = fileSystemResource.getFilename();
          helper.addAttachment(fileName, fileSystemResource);
        }
      }
      emailSender.send(message);
      response = true;
    } catch (MailException exception) {
      exception.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return response;
  }

  public Boolean constructResetTokenEmail(ResetTokenMailReqDTO request) {
    String url = "Para completar el proceso de recuperación de contraseña, ingrese en el siguiente enlace: "
        + hostFrontend + "/changePassword?token=" + request.getToken();
    String message = "Cambio de contraseña " + " \r\n" + url;
    MailReqDTO requestMail = new MailReqDTO();
    requestMail.setAsunto("Reset Password");
    requestMail.setDe(requestMail.getDe() != null ? requestMail.getDe() : noReplyMail);
    requestMail.setPara(request.getUser().getEmail());
    requestMail.setTexto(message);
    return sendSimpleMessage(requestMail);
  }
}