package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Provincia;
import com.consulti.apiconsulti.repository.ProvinciaRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvinciaValidations {

    @Autowired
    ProvinciaRepository provinciaRepository;

    public void validarProvinciaVacio(Provincia request) throws GenericException {
        Provincia objNull = new Provincia();
        if (request.equals(objNull)) {
            throw new GenericException("Se deben declarar variables para buscar la provincia",
                    CoreUtilConstants.MISSING_VALUES);
        }
    }

    public void validarGuardar(Provincia request) throws GenericException {
        if (request.getNombre() == null || request.getNombre().isEmpty()) {
            throw new GenericException("El valor nombre es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getFeCreacion() == null) {
            throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrCreacion() == null) {
            throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
    }

    public Provincia validarActualizar(Provincia request) throws GenericException {
        if (request.getId() == null) {
            throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (!provinciaRepository.existsById(request.getId())) {
            throw new GenericException("La provincia no existe", CoreUtilConstants.EXISTING_VALUES);
        }
        if (request.getFeModificacion() == null) {
            throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrModificacion() == null) {
            throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        Optional<Provincia> optionalUsuario = provinciaRepository.findById(request.getId());
        Provincia response = optionalUsuario.orElse(request);
        response.setNombre(request.getNombre() != null ? request.getNombre() : response.getNombre());
        response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
        response.setFeModificacion(
                request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
        response.setUsrModificacion(
                request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
        return response;
    }
}
