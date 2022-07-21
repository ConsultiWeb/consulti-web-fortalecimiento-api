
package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Ciudad;
import com.consulti.apiconsulti.repository.CiudadRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CiudadValidations {

    @Autowired
    CiudadRepository ciudadRepository;

    public void validarCiudadVacio(Ciudad request) throws GenericException {
        Ciudad objNull = new Ciudad();
        if (request.equals(objNull)) {
            throw new GenericException("Se deben declarar variables para buscar la ciudad",
                    CoreUtilConstants.MISSING_VALUES);
        }
    }

    public void validarGuardar(Ciudad request) throws GenericException {
        if (request.getNombre() == null || request.getNombre().isEmpty()) {
            throw new GenericException("El valor nombre es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getProvinciaId() == null) {
            throw new GenericException("El valor provincia es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getFeCreacion() == null) {
            throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrCreacion() == null) {
            throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
    }

    public Ciudad validarActualizar(Ciudad request) throws GenericException {
        if (request.getId() == null) {
            throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (!ciudadRepository.existsById(request.getId())) {
            throw new GenericException("La ciudad no existe", CoreUtilConstants.EXISTING_VALUES);
        }
        if (request.getFeModificacion() == null) {
            throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrModificacion() == null) {
            throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        Optional<Ciudad> optionalUsuario = ciudadRepository.findById(request.getId());
        Ciudad response = optionalUsuario.orElse(request);
        response.setNombre(request.getNombre() != null ? request.getNombre() : response.getNombre());
        response.setProvinciaId(request.getProvinciaId() != null ? request.getProvinciaId() : response.getProvinciaId());
        response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
        response.setFeModificacion(
                request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
        response.setUsrModificacion(
                request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
        return response;
    }
}
