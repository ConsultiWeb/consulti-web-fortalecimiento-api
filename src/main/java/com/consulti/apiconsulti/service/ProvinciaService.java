
package com.consulti.apiconsulti.service;

import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Provincia;
import org.springframework.stereotype.Service;

@Service
public interface ProvinciaService {
    List<Provincia> listar() throws GenericException;

    List<Provincia> listarPor(Provincia request) throws GenericException;

    Provincia guardar(Provincia request) throws GenericException;

    Provincia actualizar(Provincia request) throws GenericException;

    Boolean eliminar(Provincia request) throws GenericException;
}
