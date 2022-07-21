package com.consulti.apiconsulti.service;

import java.util.List;
import java.util.UUID;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.InformacionContacto;
import org.springframework.stereotype.Service;

@Service
public interface InformacionContactoService {
    List<InformacionContacto> listar() throws GenericException;

    List<InformacionContacto> listarPor(InformacionContacto request) throws GenericException;

    InformacionContacto guardar(InformacionContacto request) throws GenericException;

    InformacionContacto actualizar(InformacionContacto request) throws GenericException;

    Boolean eliminar(UUID user) throws GenericException;

}
