package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.ProcesoReclutamiento;
import com.consulti.apiconsulti.repository.CargoRepository;
import com.consulti.apiconsulti.repository.ProcesoReclutamientoRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcesoReclutamientoValidations {

    @Autowired
    ProcesoReclutamientoRepository procesoReclutamientoRepository;
    @Autowired
    CargoRepository cargoRepository;
    public void validarProcesoReclutamientoVacio(ProcesoReclutamiento request) throws GenericException {
        ProcesoReclutamiento objNull = new ProcesoReclutamiento();
        if (request.equals(objNull)) {
            throw new GenericException("Se deben declarar variables para buscar el proceso reclutamiento",
                    CoreUtilConstants.MISSING_VALUES);
        }
    }

    public void validarGuardar(ProcesoReclutamiento request) throws GenericException {
        if(!cargoRepository.existsById(request.getCargo().getId())){
            throw new GenericException("El cargo no existe", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getNombre() == null || request.getNombre().isEmpty()) {
            throw new GenericException("El valor nombre es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getCargo() == null ) {
            throw new GenericException("El valor cargo es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getFeFin() == null ) {
            throw new GenericException("El valor feFin es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getFeInicio() == null) {
            throw new GenericException("El valor feInicio es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getPublicado() == null|| request.getPublicado().isEmpty()) {
            throw new GenericException("El valor publicado es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getFeCreacion() == null) {
            throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrCreacion() == null) {
            throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
    }

    public ProcesoReclutamiento validarActualizar(ProcesoReclutamiento request) throws GenericException {
        if (request.getId() == null) {
            throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (!procesoReclutamientoRepository.existsById(request.getId())) {
            throw new GenericException("El proceso de reclutamiento no existe", CoreUtilConstants.EXISTING_VALUES);
        }
        if (request.getFeModificacion() == null) {
            throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        if (request.getUsrModificacion() == null) {
            throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
        }
        Optional<ProcesoReclutamiento> optionalUsuario = procesoReclutamientoRepository.findById(request.getId());
        ProcesoReclutamiento response = optionalUsuario.orElse(request);
        response.setNombre(request.getNombre() != null ? request.getNombre() : response.getNombre());
        response.setCargo(request.getCargo() != null ? request.getCargo() : response.getCargo());
        response.setFeFin(request.getFeFin() != null ? request.getFeFin() : response.getFeFin());
        response.setFeInicio(request.getFeInicio() != null ? request.getFeInicio() : response.getFeInicio());
        response.setPublicado(request.getPublicado()!= null ? request.getPublicado() : response.getPublicado());
        response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
        response.setFeModificacion(
                request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
        response.setUsrModificacion(
                request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
        return response;
    }
}
