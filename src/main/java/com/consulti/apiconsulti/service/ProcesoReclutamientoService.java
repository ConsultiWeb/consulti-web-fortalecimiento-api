package com.consulti.apiconsulti.service;

import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.ProcesoReclutamiento;
import org.springframework.stereotype.Service;

@Service
public interface ProcesoReclutamientoService {
    List<ProcesoReclutamiento> listar() throws GenericException;

    List<ProcesoReclutamiento> listarPor(ProcesoReclutamiento request) throws GenericException;

    ProcesoReclutamiento guardar(ProcesoReclutamiento request) throws GenericException;

    ProcesoReclutamiento actualizar(ProcesoReclutamiento request) throws GenericException;

    Boolean eliminar(ProcesoReclutamiento request) throws GenericException;
}
