package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.TipoContacto;
import com.consulti.apiconsulti.repository.TipoContactoRepository;
import com.consulti.apiconsulti.service.TipoContactoService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.TipoContactoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TipoContactoServiceImpl implements TipoContactoService {

    @Autowired
    TipoContactoRepository tipoContactoRepository;

    @Autowired
    TipoContactoValidations tipoContactoValidations;

    @Override
    public List<TipoContacto> listar() throws GenericException {
        List<TipoContacto> response;
        try {
            GeneralUtils.validarMaximoDataLista(tipoContactoRepository.count());
            response = tipoContactoRepository.findAll();
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    @Override
    public List<TipoContacto> listarPor(TipoContacto request) throws GenericException {
        List<TipoContacto> response;
        try {
            tipoContactoValidations.validarTipoContactoVacio(request);
            Example<TipoContacto> listaFiltros = Example.of(request);
            response = tipoContactoRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, TipoContacto.ID_TIPO_CONTACTO));
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public TipoContacto guardar(TipoContacto request) throws GenericException {
        TipoContacto response = new TipoContacto();
        try {
            request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
            request.setFeCreacion(new Date());
            tipoContactoValidations.validarGuardar(request);
            response = tipoContactoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public TipoContacto actualizar(TipoContacto request) throws GenericException {
        TipoContacto response = new TipoContacto();
        try {
            request.setFeModificacion(new Date());
            request = tipoContactoValidations.validarActualizar(request);
            response = tipoContactoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }

    public Boolean eliminar(TipoContacto request) throws GenericException {
        Boolean response = false;
        try {
            request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
            request.setFeModificacion(new Date());
            request = tipoContactoValidations.validarActualizar(request);
            tipoContactoRepository.saveAndFlush(request);
            response = true;
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
}
