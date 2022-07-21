package com.consulti.apiconsulti.service;

import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Faq;
import org.springframework.stereotype.Service;

@Service
public interface FaqService {
  List<Faq> listar() throws GenericException;

  List<Faq> listarPor(Faq request) throws GenericException;

  Faq guardar(Faq request) throws GenericException;

  Faq actualizar(Faq request) throws GenericException;

  Boolean eliminar(Faq request) throws GenericException;
}
