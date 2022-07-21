package com.consulti.apiconsulti.service;

import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Resume;
import com.consulti.apiconsulti.model.DetalleResumenDTO;
import org.springframework.stereotype.Service;

@Service
public interface ResumeService {
  List<Resume> listar() throws GenericException;

  List<Resume> listarPor(Resume request) throws GenericException;

  Resume guardar(Resume request) throws GenericException;

  Resume actualizar(Resume request) throws GenericException;

  Boolean eliminar(Resume request) throws GenericException;

  Resume guardarDetalleResumen(DetalleResumenDTO request) throws GenericException;
}
