package com.consulti.apiconsulti.service;

import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  List<User> listar() throws GenericException;

  List<User> listarPor(User request) throws GenericException;

  User guardar(User request) throws GenericException;

  User actualizar(User request) throws GenericException;

  Boolean eliminar(User request) throws GenericException;
}
