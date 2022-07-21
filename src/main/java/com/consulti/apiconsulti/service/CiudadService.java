package com.consulti.apiconsulti.service;

import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Ciudad;
import org.springframework.stereotype.Service;

@Service
public interface CiudadService {
    List<Ciudad> listar() throws GenericException;

    List<Ciudad> listarPor(Ciudad request) throws GenericException;

    Ciudad guardar(Ciudad request) throws GenericException;

    Ciudad actualizar(Ciudad request) throws GenericException;

    Boolean eliminar(Ciudad request) throws GenericException;
}
