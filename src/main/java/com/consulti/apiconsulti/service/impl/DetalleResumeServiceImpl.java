package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.DetalleResume;
import com.consulti.apiconsulti.repository.DetalleResumenRepository;
import com.consulti.apiconsulti.service.DetalleResumeService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.validations.DetalleResumeValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;

import org.springframework.stereotype.Service;

@Service
public class DetalleResumeServiceImpl implements DetalleResumeService {

  @Autowired
  DetalleResumenRepository detalleRepo;

  @Autowired
  DetalleResumeValidations detalleResumeValidations;

  @Override
  public List<DetalleResume> listar() throws GenericException {
    List<DetalleResume> response;
    try {
      GeneralUtils.validarMaximoDataLista(detalleRepo.count());
      response = detalleRepo.findAll();
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public List<DetalleResume> listarPor(DetalleResume request) throws GenericException {
    List<DetalleResume> response;
    try {
      detalleResumeValidations.validarVacio(request);
      Example<DetalleResume> listaFiltros = Example.of(request);
      response = detalleRepo.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, DetalleResume.ID_DETALLE_RESUME));
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public DetalleResume guardar(DetalleResume request) throws GenericException {
    DetalleResume response = new DetalleResume();
    try {
      request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      request.setFeCreacion(new Date());
      detalleResumeValidations.validarGuardar(request);
      response = detalleRepo.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public DetalleResume actualizar(DetalleResume request) throws GenericException {
    DetalleResume response = new DetalleResume();
    try {
      request.setFeModificacion(new Date());
      request = detalleResumeValidations.validarActualizar(request);
      response = detalleRepo.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Boolean eliminar(DetalleResume request) throws GenericException {
    Boolean response = false;
    try {
      request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
      request.setFeModificacion(new Date());
      request = detalleResumeValidations.validarActualizar(request);
      detalleRepo.saveAndFlush(request);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

}
