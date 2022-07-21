package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Ciudad;
import com.consulti.apiconsulti.repository.CiudadRepository;
import com.consulti.apiconsulti.service.CiudadService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.CiudadValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    CiudadRepository ciudadRepository;

    @Autowired
    CiudadValidations ciudadValidations;

    /* Funcion que lista todas las ciudades
        @param: ninguno
        @return: List<Ciudad>
    */
    @Override
    public List<Ciudad> listar() throws GenericException {
        List<Ciudad> response;
        try {
            GeneralUtils.validarMaximoDataLista(ciudadRepository.count());
            response = ciudadRepository.findAll();
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que lista todas las ciudades por filtro
        @param: Ciudad request
        @return: List<Ciudad>
    */
    @Override
    public List<Ciudad> listarPor(Ciudad request) throws GenericException {
        List<Ciudad> response;
        try {
            ciudadValidations.validarCiudadVacio(request);
            Example<Ciudad> listaFiltros = Example.of(request);
            response = ciudadRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, Ciudad.ID_CIUDAD));
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que guarda una ciudad
        @param: Ciudad request
        @return: Ciudad
    */
    public Ciudad guardar(Ciudad request) throws GenericException {
        Ciudad response = new Ciudad();
        try {
            request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
            request.setFeCreacion(new Date());
            ciudadValidations.validarGuardar(request);
            response = ciudadRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que actualiza una ciudad
        @param: Ciudad request
        @return: Ciudad
    */
    public Ciudad actualizar(Ciudad request) throws GenericException {
        Ciudad response = new Ciudad();
        try {
            request.setFeModificacion(new Date());
            request = ciudadValidations.validarActualizar(request);
            response = ciudadRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que elimina una ciudad
        @param: Ciudad request
        @return: Ciudad
    */
    public Boolean eliminar(Ciudad request) throws GenericException {
        Boolean response = false;
        try {
            request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
            request.setFeModificacion(new Date());
            request = ciudadValidations.validarActualizar(request);
            ciudadRepository.saveAndFlush(request);
            response = true;
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
}
