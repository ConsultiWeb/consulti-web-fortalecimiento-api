package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Faq;
import com.consulti.apiconsulti.repository.FaqRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FaqValidations {

  @Autowired
  FaqRepository userRepository;

  public void validarFaqVacio(Faq request) throws GenericException {
    Faq objNull = new Faq();
    if (request.equals(objNull)) {
      throw new GenericException("Se deben declarar variables para buscar el usuario",
          CoreUtilConstants.MISSING_VALUES);
    }
  }

  public void validarGuardar(Faq request) throws GenericException {
    if (request.getPregunta() == null || request.getPregunta().isEmpty()) {
      throw new GenericException("El valor pregunta es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getRespuesta() == null || request.getRespuesta().isEmpty()) {
      throw new GenericException("El valor respuesta es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getFeCreacion() == null) {
      throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrCreacion() == null) {
      throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
  }

  public Faq validarActualizar(Faq request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!userRepository.existsById(request.getId())) {
      throw new GenericException("El faq no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    if (request.getFeModificacion() == null) {
      throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrModificacion() == null) {
      throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    Optional<Faq> optionalUsuario = userRepository.findById(request.getId());
    Faq response = optionalUsuario.orElse(request);
    response.setPregunta(request.getPregunta() != null ? request.getPregunta() : response.getPregunta());
    response.setRespuesta(request.getRespuesta() != null ? request.getRespuesta() : response.getRespuesta());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    response.setFeModificacion(
        request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
    response.setUsrModificacion(
        request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
    return response;
  }
}
