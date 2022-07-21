package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;
import com.consulti.apiconsulti.config.exception.GenericException;


import com.consulti.apiconsulti.entity.InformacionContacto;
import com.consulti.apiconsulti.repository.InformacionContactoRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InformacionContactoValidations {

    @Autowired
    InformacionContactoRepository informacionContactoRepository;

    public void validarInformacionContactoVacio(InformacionContacto request) throws GenericException {
        InformacionContacto objNull = new InformacionContacto();
        if (request.equals(objNull)) {
            throw new GenericException("Se deben declarar variables para buscar la informacion de contacto",
                    CoreUtilConstants.MISSING_VALUES);
        }
    }

    public void validarGuardar(InformacionContacto request) throws GenericException {
        if (request.getValor() == null || request.getValor().isEmpty()) {
            throw new GenericException("El valor es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUserId() == null) {
            throw new GenericException("El valor user es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getTipoContactoId() == null) {
            throw new GenericException("El valor tipo_contacto es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getFeCreacion() == null) {
            throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrCreacion() == null) {
            throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
    }

    public InformacionContacto validarActualizar(InformacionContacto request) throws GenericException {
        if (request.getId() == null) {
            throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (!informacionContactoRepository.existsById(request.getId())) {
            throw new GenericException("La informacion de contacto no existe", CoreUtilConstants.EXISTING_VALUES);
        }
        if (request.getFeModificacion() == null) {
            throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrModificacion() == null) {
            throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        Optional<InformacionContacto> optionalUsuario = informacionContactoRepository.findById(request.getId());
        InformacionContacto response = optionalUsuario.orElse(request);
        response.setValor(request.getValor() != null ? request.getValor() : response.getValor());
        response.setUserId(request.getUserId() != null ? request.getUserId() : response.getUserId());
        response.setTipoContactoId(request.getTipoContactoId() != null ? request.getTipoContactoId() : response.getTipoContactoId());
        response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
        response.setFeModificacion(
                request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
        response.setUsrModificacion(
                request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
        return response;
    }
}
