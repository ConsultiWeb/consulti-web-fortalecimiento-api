package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Cargo;
import com.consulti.apiconsulti.repository.CargoRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoValidations {

    @Autowired
    CargoRepository cargoRepository;

    public void validarCargoVacio(Cargo request) throws GenericException {
        Cargo objNull = new Cargo();
        if (request.equals(objNull)) {
            throw new GenericException("Se deben declarar variables para buscar el cargo",
                    CoreUtilConstants.MISSING_VALUES);
        }
    }

    public void validarGuardar(Cargo request) throws GenericException {
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

    public Cargo validarActualizar(Cargo request) throws GenericException {
        if (request.getId() == null) {
            throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (!cargoRepository.existsById(request.getId())) {
            throw new GenericException("El cargo no existe", CoreUtilConstants.EXISTING_VALUES);
        }
        if (request.getFeModificacion() == null) {
            throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrModificacion() == null) {
            throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        Optional<Cargo> optionalCargo = cargoRepository.findById(request.getId());
        Cargo response = optionalCargo.orElse(request);
        response.setNombre(request.getNombre() != null ? request.getNombre() : response.getNombre());
        response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
        response.setFeModificacion(
                request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
        response.setUsrModificacion(
                request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
        return response;
    }
}
