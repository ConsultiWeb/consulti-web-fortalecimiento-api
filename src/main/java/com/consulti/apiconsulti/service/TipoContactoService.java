package com.consulti.apiconsulti.service;

import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.TipoContacto;
import org.springframework.stereotype.Service;

@Service
public interface TipoContactoService {
    List<TipoContacto> listar() throws GenericException;

    List<TipoContacto> listarPor(TipoContacto request) throws GenericException;

    TipoContacto guardar(TipoContacto request) throws GenericException;

    TipoContacto actualizar(TipoContacto request) throws GenericException;

    Boolean eliminar(TipoContacto request) throws GenericException;
}
