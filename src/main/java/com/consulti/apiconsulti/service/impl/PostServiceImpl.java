package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Post;
import com.consulti.apiconsulti.model.PostReqDTO;
import com.consulti.apiconsulti.repository.PostRepository;
import com.consulti.apiconsulti.service.PostService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.PostValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  PostRepository postRepository;

  @Autowired
  PostValidations postValidations;

  @Override
  public List<Post> listar() throws GenericException {
    List<Post> response;
    try {
      GeneralUtils.validarMaximoDataLista(postRepository.count());
      response = postRepository.findAll();
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public List<Post> listarPor(Post request) throws GenericException {
    List<Post> response;
    try {
      postValidations.validarVacio(request);
      Example<Post> listaFiltros = Example.of(request);
      response = postRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, Post.ID_POST));
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Post guardar(PostReqDTO request) throws GenericException {
    Post response = new Post();
    try {
      Post post = new Post();
      post.setContenido(request.getContenido());
      post.setTitulo(request.getTitulo());
      post.setUsrCreacion(request.getUsrCreacion());
      post.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      post.setFeCreacion(new Date());
      postValidations.validarGuardar(post);
      response = postRepository.save(post);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Post actualizar(Post request) throws GenericException {
    Post response = new Post();
    try {
      request.setFeModificacion(new Date());
      request = postValidations.validarActualizar(request);
      response = postRepository.save(request);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Boolean eliminar(Post request) throws GenericException {
    Boolean response = false;
    try {
      request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
      request.setFeModificacion(new Date());
      request = postValidations.validarActualizar(request);
      postRepository.saveAndFlush(request);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
}
