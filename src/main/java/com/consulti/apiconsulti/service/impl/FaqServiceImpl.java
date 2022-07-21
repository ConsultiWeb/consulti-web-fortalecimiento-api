package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Faq;
import com.consulti.apiconsulti.repository.FaqRepository;
import com.consulti.apiconsulti.service.FaqService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.FaqValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FaqServiceImpl implements FaqService {

  @Autowired
  FaqRepository faqRepository;

  @Autowired
  FaqValidations faqValidations;
  /* Funcion que lista los FAQ
        @param: ninguno
        @return: List<Faq>
    */
  @Override
  public List<Faq> listar() throws GenericException {
    List<Faq> response;
    try {
      GeneralUtils.validarMaximoDataLista(faqRepository.count());
      response = faqRepository.findAll();
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  /* Funcion que lista los FAQ por filtro
        @param: Faq request
        @return: List<Faq>
    */
  @Override
  public List<Faq> listarPor(Faq request) throws GenericException {
    List<Faq> response;
    try {
      faqValidations.validarFaqVacio(request);
      Example<Faq> listaFiltros = Example.of(request);
      response = faqRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, Faq.ID_FAQ));
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
 /* Funcion que guarda un FAQ
        @param: Faq request
        @return: Faq
    */
  public Faq guardar(Faq request) throws GenericException {
    Faq response = new Faq();
    try {
      request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      request.setFeCreacion(new Date());
      faqValidations.validarGuardar(request);
      response = faqRepository.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  /* Funcion que guarda un FAQ
        @param: Faq request
        @return: Faq
    */
  public Faq actualizar(Faq request) throws GenericException {
    Faq response = new Faq();
    try {
      request.setFeModificacion(new Date());
      request = faqValidations.validarActualizar(request);
      response = faqRepository.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  /* Funcion que guarda un FAQ
        @param: Faq request
        @return: Faq
    */
  public Boolean eliminar(Faq request) throws GenericException {
    Boolean response = false;
    try {
      request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
      request.setFeModificacion(new Date());
      request = faqValidations.validarActualizar(request);
      faqRepository.saveAndFlush(request);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
}
