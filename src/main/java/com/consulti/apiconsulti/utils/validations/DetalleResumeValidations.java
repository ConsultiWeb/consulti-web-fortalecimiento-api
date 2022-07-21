package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.DetalleResume;
import com.consulti.apiconsulti.repository.DetalleResumenRepository;
import com.consulti.apiconsulti.repository.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

@Component
public class DetalleResumeValidations {
  @Autowired
  DetalleResumenRepository detalleRepo;

  @Autowired
  ResumeRepository resumenRepo;

  public void validarVacio(DetalleResume request) throws GenericException {
    DetalleResume objNull = new DetalleResume();
    if (request.equals(objNull)) {
      throw new GenericException("Se deben declarar variables para buscar el usuario",
          CoreUtilConstants.MISSING_VALUES);
    }
  }

  public void validarGuardar(DetalleResume request) throws GenericException {
    if (request.getNombre() == null) {
      throw new GenericException("El valor nombre es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getResumeId() == null) {
      throw new GenericException("El valor resumeId es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getValor() == null) {
      throw new GenericException("El valor valor es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getTipo() == null) {
      throw new GenericException("El valor tipo es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getFeCreacion() == null) {
      throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrCreacion() == null) {
      throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getEstado() == null) {
      throw new GenericException("El valor estado es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!resumenRepo.existsById(request.getResumeId())) {
      throw new GenericException("El resumen no existe", CoreUtilConstants.EXISTING_VALUES);
    }
  }

  public DetalleResume validarActualizar(DetalleResume request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getResumeId() == null) {
      throw new GenericException("El valor resumeId es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!resumenRepo.existsById(request.getResumeId())) {
      throw new GenericException("El resumen no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    if (request.getFeModificacion() == null) {
      throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrModificacion() == null) {
      throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    Optional<DetalleResume> optionalUsuario = detalleRepo.findById(request.getId());
    DetalleResume response = optionalUsuario.orElse(request);
    response.setNombre(request.getNombre() != null ? request.getNombre() : response.getNombre());
    response.setValor(request.getValor() != null ? request.getValor() : response.getValor());
    response.setTipo(request.getTipo() != null ? request.getTipo() : response.getTipo());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    response.setFeModificacion(
        request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
    response.setUsrModificacion(
        request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
    return response;
  }
}
