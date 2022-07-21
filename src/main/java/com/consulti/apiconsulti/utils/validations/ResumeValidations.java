package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Resume;
import com.consulti.apiconsulti.repository.ResumeRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResumeValidations {

  @Autowired
  ResumeRepository resumeRepo;

  public void validarVacio(Resume request) throws GenericException {
    Resume objNull = new Resume();
    if (request.equals(objNull)) {
      throw new GenericException("Se deben declarar variables para buscar el resume", CoreUtilConstants.MISSING_VALUES);
    }
  }

  public void validarGuardar(Resume request) throws GenericException {
    if (request.getFeCreacion() == null) {
      throw new GenericException("La fecha de creación es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrCreacion() == null) {
      throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getEstado() == null) {
      throw new GenericException("El valor estado es requerido", CoreUtilConstants.MISSING_VALUES);
    }
  }

  public Resume validarActualizar(Resume request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!resumeRepo.existsById(request.getId())) {
      throw new GenericException("El resume no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    if (request.getFeModificacion() == null) {
      throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrModificacion() == null) {
      throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    Optional<Resume> optionalLabel = resumeRepo.findById(request.getId());
    Resume response = optionalLabel.orElse(request);
    response.setFeCreacion(request.getFeCreacion() != null ? request.getFeCreacion() : response.getFeCreacion());
    response.setAniosExperiencia( request.getAniosExperiencia()!=0?  request.getAniosExperiencia() :
     response.getAniosExperiencia());
    //response.setArchivo(request.getArchivo() != null ? request.getArchivo() : response.getArchivo());
    response.setUrlExtra(request.getUrlExtra() != null ? request.getUrlExtra() : response.getUrlExtra());
    response.setUser(request.getUser() != null ? request.getUser() : response.getUser());
    response.setUrlPortafolio(
        request.getUrlPortafolio() != null ? request.getUrlPortafolio() : response.getUrlPortafolio());
    response.setDescripcion(request.getDescripcion() != null ? request.getDescripcion() : response.getDescripcion());
    response.setUsrModificacion(
        request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
    response.setFeModificacion(
        request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    response.setTecnologias(request.getTecnologias() != null ? request.getTecnologias() : response.getTecnologias());
    return response;
  }
}
