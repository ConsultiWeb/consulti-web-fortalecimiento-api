package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.TipoContacto;
import com.consulti.apiconsulti.repository.TipoContactoRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TipoContactoValidations {

    @Autowired
    TipoContactoRepository tipoContactoRepository;

    public void validarTipoContactoVacio(TipoContacto request) throws GenericException {
        TipoContacto objNull = new TipoContacto();
        if (request.equals(objNull)) {
            throw new GenericException("Se deben declarar variables para buscar el tipo contacto",
                    CoreUtilConstants.MISSING_VALUES);
        }
    }

    public void validarGuardar(TipoContacto request) throws GenericException {
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

    public TipoContacto validarActualizar(TipoContacto request) throws GenericException {
        if (request.getId() == null) {
            throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (!tipoContactoRepository.existsById(request.getId())) {
            throw new GenericException("El tipo contacto no existe", CoreUtilConstants.EXISTING_VALUES);
        }
        if (request.getFeModificacion() == null) {
            throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrModificacion() == null) {
            throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        Optional<TipoContacto> optionalUsuario = tipoContactoRepository.findById(request.getId());
        TipoContacto response = optionalUsuario.orElse(request);
        response.setNombre(request.getNombre() != null ? request.getNombre() : response.getNombre());
        response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
        response.setFeModificacion(
                request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
        response.setUsrModificacion(
                request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
        return response;
    }
}
