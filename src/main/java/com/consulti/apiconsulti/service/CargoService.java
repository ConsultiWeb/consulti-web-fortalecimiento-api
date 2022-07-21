package com.consulti.apiconsulti.service;

import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Cargo;
import org.springframework.stereotype.Service;

@Service
public interface CargoService {
    List<Cargo> listar() throws GenericException;

    List<Cargo> listarPor(Cargo request) throws GenericException;

    Cargo guardar(Cargo request) throws GenericException;

    Cargo actualizar(Cargo request) throws GenericException;

    Boolean eliminar(Cargo request) throws GenericException;
}
