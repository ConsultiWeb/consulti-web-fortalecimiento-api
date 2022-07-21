package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.HistoricoContacto;
import com.consulti.apiconsulti.repository.HistoricoContactoRepository;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HistoricoContactoValidations {

    @Autowired
    HistoricoContactoRepository historicoContactoRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void validarHistoricoContactoVacio(HistoricoContacto request) throws GenericException {
        HistoricoContacto objNull = new HistoricoContacto();
        if (request.equals(objNull)) {
            throw new GenericException("Se deben declarar variables para buscar el registro",
                    CoreUtilConstants.MISSING_VALUES);
        }
    }

    public void validarGuardar(HistoricoContacto request) throws GenericException {
        if (request.getEmail() == null|| request.getIdentificacion().length() == 0) {
            throw new GenericException("El valor email es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrCreacion() == null) {
            throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getFeCreacion() == null) {
            throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getEstado() == null) {
            throw new GenericException("El valor estado es requerido", CoreUtilConstants.MISSING_VALUES);
        }
    }
    public HistoricoContacto validarActualizar(HistoricoContacto request) throws GenericException {
        if (request.getId() == null) {
            throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (!historicoContactoRepository.existsById(request.getId())) {
            throw new GenericException("El contacto no existe", CoreUtilConstants.EXISTING_VALUES);
        }
        if (request.getFeModificacion() == null) {
            throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrModificacion() == null) {
            throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        Optional<HistoricoContacto> optionalUsuario = historicoContactoRepository.findById(request.getId());
        HistoricoContacto response = optionalUsuario.orElse(request);
        response.setEmail(request.getEmail() != null ? request.getEmail() : response.getEmail());
        response.setNombres(request.getNombres() != null ? request.getNombres() : response.getNombres());
        response.setApellidos(request.getApellidos() != null ? request.getApellidos() : response.getApellidos());
        response.setGenero(request.getGenero() != null ? request.getGenero() : response.getGenero());
        response.setCiudadId(request.getCiudadId() != null ? request.getCiudadId() : response.getCiudadId());
        response.setTelefono(request.getTelefono() != null ? request.getTelefono() : response.getTelefono());
        response
                .setFeNacimiento(request.getFeNacimiento() != null ? request.getFeNacimiento() : response.getFeNacimiento());
        response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
        response.setFeModificacion(
                request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
        response.setUsrModificacion(
                request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
        return response;
    }
}
