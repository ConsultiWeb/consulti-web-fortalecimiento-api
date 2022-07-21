package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Resume;
import com.consulti.apiconsulti.model.DetalleResumenDTO;
import com.consulti.apiconsulti.repository.ResumeRepository;
import com.consulti.apiconsulti.service.DetalleResumeService;
import com.consulti.apiconsulti.service.ResumeService;
import com.consulti.apiconsulti.service.UserService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.ResumeValidations;
import com.consulti.apiconsulti.utils.validations.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {

  @Autowired
  ResumeRepository resumeRepo;

  @Autowired
  ResumeValidations resumeValidations;

  @Autowired
  UserValidations userValidations;

  @Autowired
  DetalleResumeService detalleResumeService;

  @Autowired
  UserService userService;
  @Override
  public List<Resume> listar() throws GenericException {
    List<Resume> response;
    try {
      GeneralUtils.validarMaximoDataLista(resumeRepo.count());
      response = resumeRepo.findAll();
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public List<Resume> listarPor(Resume request) throws GenericException {
    List<Resume> response;
    try {
      resumeValidations.validarVacio(request);
      Example<Resume> listaFiltros = Example.of(request);
      response = resumeRepo.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, Resume.ID_RESUME));
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  @Override
  public Resume guardarDetalleResumen(DetalleResumenDTO request) throws GenericException {
    Resume response = new Resume();
    try {

      response = request.getResume();
      response.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      response.setFeCreacion(new Date());
      request.getUser().setFeModificacion(new Date());
      request.getUser().setUsrModificacion(request.getResume().getUsrCreacion());

      if(request.getResume().getId()!=null){
        response.setFeModificacion(new Date());
        response.setUsrModificacion(request.getResume().getUsrCreacion());
        resumeValidations.validarActualizar(response);
        resumeRepo.save(response);
      }else{
        resumeValidations.validarGuardar(response);
        resumeRepo.save(response);
      }
      response.setUser(userService.actualizar(request.getUser()));

    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  @Override
  public Resume guardar(Resume request) throws GenericException {
    Resume response = new Resume();
    try {
      request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      request.setFeCreacion(new Date());
      resumeValidations.validarGuardar(request);
      response = resumeRepo.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Resume actualizar(Resume request) throws GenericException {
    Resume response = new Resume();
    try {
      request.setFeModificacion(new Date());
      request = resumeValidations.validarActualizar(request);
      response = resumeRepo.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Boolean eliminar(Resume request) throws GenericException {
    Boolean response = false;
    try {
      request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
      request.setFeModificacion(new Date());
      request = resumeValidations.validarActualizar(request);
      resumeRepo.saveAndFlush(request);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

}
