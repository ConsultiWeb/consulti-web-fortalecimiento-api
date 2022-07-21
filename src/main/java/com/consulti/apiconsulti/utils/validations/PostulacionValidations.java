package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Postulacion;
import com.consulti.apiconsulti.entity.Resume;
import com.consulti.apiconsulti.repository.PostulacionRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostulacionValidations {

  @Autowired
  PostulacionRepository postulacionRepo;

  @Autowired
  UserValidations userValidations;

  public void validarVacio(Postulacion request) throws GenericException {
    Postulacion objNull = new Postulacion();
    if (request.equals(objNull)) {
      throw new GenericException("Se deben declarar variables para buscar el usuario",
          CoreUtilConstants.MISSING_VALUES);
    }
  }

  public void validarGuardar(Postulacion request) throws GenericException {
    if (request.getFeCreacion() == null) {
      throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrCreacion() == null) {
      throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getEstado() == null) {
      throw new GenericException("El valor estado es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getResumen() == null) {
      throw new GenericException("El valor resumen es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUser() == null) {
      throw new GenericException("El valor user es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getTecnologias() == null) {
      throw new GenericException("El valor tecnologías es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getTecnologias().isEmpty()) {
      throw new GenericException("Se debe ingresar al menos 1 tecnología", CoreUtilConstants.MISSING_VALUES);
    }
  }

  public Postulacion validarActualizar(Postulacion request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!postulacionRepo.existsById(request.getId())) {
      throw new GenericException("La postulación no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    if (request.getFeModificacion() == null) {
      throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrModificacion() == null) {
      throw new GenericException("El valor usrModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getTecnologias() != null && request.getTecnologias().isEmpty()) {
      throw new GenericException("Se debe ingresar al menos 1 tecnología", CoreUtilConstants.MISSING_VALUES);
    }
    Optional<Postulacion> optionalLabel = postulacionRepo.findById(request.getId());
    Postulacion response = optionalLabel.orElse(request);
    response.setFeCreacion(request.getFeCreacion() != null ? request.getFeCreacion() : response.getFeCreacion());
    response.setUsrModificacion(
        request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
    response.setResumen(request.getResumen() != null ? request.getResumen() : response.getResumen());
    response.setFeModificacion(
        request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    response.setUser(request.getUser() != null ? request.getUser() : response.getUser());
    response.setResumen(request.getResumen() != null ? request.getResumen() : response.getResumen());
    response.setTecnologias(request.getTecnologias() != null ? request.getTecnologias() : response.getTecnologias());
    return response;
  }

  public Postulacion validarEliminar(Postulacion request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!postulacionRepo.existsById(request.getId())) {
      throw new GenericException("La postulación no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    if (request.getFeModificacion() == null) {
      throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrModificacion() == null) {
      throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    Optional<Postulacion> optionalLabel = postulacionRepo.findById(request.getId());
    Postulacion response = optionalLabel.orElse(request);
    response.setFeCreacion(request.getFeCreacion() != null ? request.getFeCreacion() : response.getFeCreacion());
    response.setUsrModificacion(
        request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
    response.setUser(request.getUser() != null ? request.getUser() : response.getUser());
    response.setFeModificacion(
        request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    Resume resumen = request.getResumen() != null ? request.getResumen() : response.getResumen();
    resumen.setEstado(request.getEstado());
    response.setResumen(resumen);
    return response;
  }
}
