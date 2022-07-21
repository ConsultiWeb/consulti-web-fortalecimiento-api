package com.consulti.apiconsulti.service;

import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.DetalleResume;

import org.springframework.stereotype.Service;

@Service
public interface DetalleResumeService {
  List<DetalleResume> listar() throws GenericException;

  List<DetalleResume> listarPor(DetalleResume request) throws GenericException;

  DetalleResume guardar(DetalleResume request) throws GenericException;

  DetalleResume actualizar(DetalleResume request) throws GenericException;

  Boolean eliminar(DetalleResume request) throws GenericException;

}
