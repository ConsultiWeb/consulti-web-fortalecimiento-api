package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Provincia;
import com.consulti.apiconsulti.repository.ProvinciaRepository;
import com.consulti.apiconsulti.service.ProvinciaService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.ProvinciaValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    ProvinciaRepository provinciaRepository;

    @Autowired
    ProvinciaValidations provinciaValidations;

    @Override
    public List<Provincia> listar() throws GenericException {
        List<Provincia> response;
        try {
            GeneralUtils.validarMaximoDataLista(provinciaRepository.count());
            response = provinciaRepository.findAll();
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    @Override
    public List<Provincia> listarPor(Provincia request) throws GenericException {
        List<Provincia> response;
        try {
            provinciaValidations.validarProvinciaVacio(request);
            Example<Provincia> listaFiltros = Example.of(request);
            response = provinciaRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, Provincia.ID_PROVINCIA));
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public Provincia guardar(Provincia request) throws GenericException {
        Provincia response = new Provincia();
        try {
            request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
            request.setFeCreacion(new Date());
            provinciaValidations.validarGuardar(request);
            response = provinciaRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public Provincia actualizar(Provincia request) throws GenericException {
        Provincia response = new Provincia();
        try {
            request.setFeModificacion(new Date());
            request = provinciaValidations.validarActualizar(request);
            response = provinciaRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public Boolean eliminar(Provincia request) throws GenericException {
        Boolean response = false;
        try {
            request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
            request.setFeModificacion(new Date());
            request = provinciaValidations.validarActualizar(request);
            provinciaRepository.saveAndFlush(request);
            response = true;
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
}
