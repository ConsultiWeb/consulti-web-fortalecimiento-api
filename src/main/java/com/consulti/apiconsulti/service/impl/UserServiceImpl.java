package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.repository.UserRepository;
import com.consulti.apiconsulti.service.InformacionContactoService;
import com.consulti.apiconsulti.service.MailService;
import com.consulti.apiconsulti.service.UserService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private JavaMailSender emailSender;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MailService mailService;

  @Autowired
  UserValidations userValidations;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Value("${mail.de}")
  String noReplyMail;

  @Autowired
  InformacionContactoService informacionContactoService;

  @Override
  public List<User> listar() throws GenericException {
    List<User> response;
    try {
      GeneralUtils.validarMaximoDataLista(userRepository.count());
      response = userRepository.findAll();
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public List<User> listarPor(User request) throws GenericException {
    List<User> response;
    try {
      userValidations.validarUsuarioVacio(request);
      Example<User> listaFiltros = Example.of(request);
      response = userRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, User.ID_USER));
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  public User guardar(User request) throws GenericException {
    User response = new User();
    try {
      request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
      request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      request.setFeCreacion(new Date());
      request.setUsrCreacion(request.getUsrCreacion() == null ? request.getUsername() : request.getUsrCreacion());
      userValidations.validarGuardar(request);
      response = userRepository.saveAndFlush(request);
      //GUARDO LOS CONTACTOS

      mailService.sendRawMessage(response.getEmail());
      // Guardo el historial del elemento
      // InfoHistorialElemento historial = new InfoHistorialElemento();
      // historial.setElementoId(response.getIdElemento());
      // historial.setEstadoElemento(StatusHandler.Activo.toString());
      // historial.setObservacion("Se creó el elemento");
      // historial.setUsrCreacion(response.getUsrCreacion());
      // historial.setIpCreacion(response.getIpCreacion());
      // infoHistorialElementoService.guardar(historial);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  public User actualizar(User request) throws GenericException {
    User response = new User();
    try {
      request.setFeModificacion(new Date());
      request = userValidations.validarActualizar(request);
      response = userRepository.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  public Boolean eliminar(User request) throws GenericException {
    Boolean response = false;
    try {
      request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
      request.setFeModificacion(new Date());
      request = userValidations.validarActualizar(request);
      userRepository.saveAndFlush(request);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
}
