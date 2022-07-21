
package com.consulti.apiconsulti.service;

import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Post;
import com.consulti.apiconsulti.model.PostReqDTO;

import org.springframework.stereotype.Service;

@Service
public interface PostService {
  List<Post> listar() throws GenericException;

  List<Post> listarPor(Post request) throws GenericException;

  Post guardar(PostReqDTO request) throws GenericException;

  Post actualizar(Post request) throws GenericException;

  Boolean eliminar(Post request) throws GenericException;
}
