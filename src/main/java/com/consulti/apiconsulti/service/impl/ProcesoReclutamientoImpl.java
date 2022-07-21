package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Cargo;
import com.consulti.apiconsulti.entity.ProcesoReclutamiento;
import com.consulti.apiconsulti.repository.ProcesoReclutamientoRepository;
import com.consulti.apiconsulti.service.ProcesoReclutamientoService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.ProcesoReclutamientoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProcesoReclutamientoImpl implements ProcesoReclutamientoService {

    @Autowired
    ProcesoReclutamientoRepository procesoReclutamientoRepository;

    @Autowired
    ProcesoReclutamientoValidations procesoReclutamientoValidations;


    @Override
    public List<ProcesoReclutamiento> listar() throws GenericException {
        List<ProcesoReclutamiento> response;
        try {
            GeneralUtils.validarMaximoDataLista(procesoReclutamientoRepository.count());
            response = procesoReclutamientoRepository.findAll();
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    @Override
    public List<ProcesoReclutamiento> listarPor(ProcesoReclutamiento request) throws GenericException {
        List<ProcesoReclutamiento> response;
        try {
            procesoReclutamientoValidations.validarProcesoReclutamientoVacio(request);
            Example<ProcesoReclutamiento> listaFiltros = Example.of(request);
            response = procesoReclutamientoRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, ProcesoReclutamiento.ID_PROCESO_RECLUTAMIENTO));
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public ProcesoReclutamiento guardar(ProcesoReclutamiento request) throws GenericException {
        ProcesoReclutamiento response = new ProcesoReclutamiento();
        try {
            request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
            request.setFeCreacion(new Date());
            procesoReclutamientoValidations.validarGuardar(request);
            response = procesoReclutamientoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public ProcesoReclutamiento actualizar(ProcesoReclutamiento request) throws GenericException {
        ProcesoReclutamiento response = new ProcesoReclutamiento();
        Cargo cargoReq = new Cargo();
        try {
            cargoReq.setId(request.getCargo().getId());
            request.setFeModificacion(new Date());
            request = procesoReclutamientoValidations.validarActualizar(request);
            response = procesoReclutamientoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public Boolean eliminar(ProcesoReclutamiento request) throws GenericException {
        Boolean response = false;
        try {
            request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
            request.setFeModificacion(new Date());
            request = procesoReclutamientoValidations.validarActualizar(request);
            procesoReclutamientoRepository.saveAndFlush(request);
            response = true;
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
}
