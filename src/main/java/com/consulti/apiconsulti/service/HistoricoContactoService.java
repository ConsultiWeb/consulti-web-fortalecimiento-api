package com.consulti.apiconsulti.service;

import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.HistoricoContacto;
import org.springframework.stereotype.Service;

@Service
public interface HistoricoContactoService {
    List<HistoricoContacto> listar() throws GenericException;

    List<HistoricoContacto> listarPor(HistoricoContacto request) throws GenericException;

    HistoricoContacto guardar(HistoricoContacto request) throws GenericException;

    HistoricoContacto actualizar(HistoricoContacto request) throws GenericException;

    Boolean eliminar(HistoricoContacto request) throws GenericException;
}
