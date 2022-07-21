package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Label;
import com.consulti.apiconsulti.repository.LabelRepositoy;
import com.consulti.apiconsulti.service.LabelService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.LabelValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {

  @Autowired
  LabelRepositoy labelRepository;

  @Autowired
  LabelValidations labelValidations;

  public List<Label> listar() throws GenericException {
    List<Label> response;
    try {
      GeneralUtils.validarMaximoDataLista(labelRepository.count());
      response = labelRepository.findAll();
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public List<Label> listarPor(Label request) throws GenericException {
    List<Label> response;
    try {
      labelValidations.validarVacio(request);
      Example<Label> listaFiltros = Example.of(request);
      response = labelRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, Label.ID_LABEL));
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Label guardar(Label request) throws GenericException {
    Label response = new Label();
    try {
      request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      request.setFeCreacion(new Date());
      labelValidations.validarGuardar(request);
      response = labelRepository.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Label actualizar(Label request) throws GenericException {
    Label response = new Label();
    try {
      request = labelValidations.validarActualizar(request);
      response = labelRepository.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Boolean eliminar(Label request) throws GenericException {
    Boolean response = false;
    try {
      request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
      request = labelValidations.validarActualizar(request);
      labelRepository.saveAndFlush(request);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
}
