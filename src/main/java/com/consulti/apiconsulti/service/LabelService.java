package com.consulti.apiconsulti.service;

import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Label;

import org.springframework.stereotype.Service;

@Service
public interface LabelService {
  List<Label> listar() throws GenericException;

  List<Label> listarPor(Label request) throws GenericException;

  Label guardar(Label request) throws GenericException;

  Label actualizar(Label request) throws GenericException;

  Boolean eliminar(Label request) throws GenericException;
}
