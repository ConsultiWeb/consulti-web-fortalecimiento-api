package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Label;
import com.consulti.apiconsulti.repository.LabelRepositoy;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabelValidations {
  @Autowired
  LabelRepositoy labelRepository;

  public void validarVacio(Label request) throws GenericException {
    Label objNull = new Label();
    if (request.equals(objNull)) {
      throw new GenericException("Se deben declarar variables para buscar la etiqueta",
          CoreUtilConstants.MISSING_VALUES);
    }
  }

  public void validarGuardar(Label request) throws GenericException {
    if (request.getNombre() == null || request.getNombre().isEmpty()) {
      throw new GenericException("El valor nombre es requerido", CoreUtilConstants.MISSING_VALUES);
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
  }

  public Label validarActualizar(Label request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getNombre() == null) {
      throw new GenericException("El valor nombre es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!labelRepository.existsById(request.getId())) {
      throw new GenericException("El la etiqueta no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    Optional<Label> optionalLabel = labelRepository.findById(request.getId());
    Label response = optionalLabel.orElse(request);
    response.setNombre(request.getNombre() != null ? request.getNombre() : response.getNombre());
    response.setTipo(request.getTipo() != null ? request.getTipo() : response.getTipo());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    return response;
  }
}
