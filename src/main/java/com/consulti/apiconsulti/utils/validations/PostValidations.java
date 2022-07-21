package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Post;
import com.consulti.apiconsulti.repository.PostRepository;
import com.consulti.apiconsulti.repository.UserRepository;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostValidations {
  @Autowired
  PostRepository postRepository;

  @Autowired
  UserRepository userRepository;

  public void validarVacio(Post request) throws GenericException {
    Post objNull = new Post();
    if (request.equals(objNull)) {
      throw new GenericException("Se deben declarar variables para buscar el usuario",
          CoreUtilConstants.MISSING_VALUES);
    }
  }

  public void validarGuardar(Post request) throws GenericException {
    if (request.getTitulo() == null) {
      throw new GenericException("El valor titulo es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getContenido() == null) {
      throw new GenericException("El valor contenido es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    // if (request.getEtiqueta() == null || request.getEtiqueta().getEstado() !=
    // StatusHandlerEnum.ACTIVO.getValue()) {
    // throw new GenericException("El valor etiqueta es requerido",
    // CoreUtilConstants.MISSING_VALUES);
    // }
    // if (request.getUser() == null) {
    // throw new GenericException("El valor user es requerido",
    // CoreUtilConstants.MISSING_VALUES);
    // }
    // if (request.getUser().getId() == null) {
    // throw new GenericException("El id del usuario es requerido",
    // CoreUtilConstants.MISSING_VALUES);
    // }
    // if (userRepository.existsById(request.getUser().getId())) {
    // throw new GenericException("El valor usuario no existe",
    // CoreUtilConstants.INFORMATIVE_VALUES);
    // }
    // if (request.getUser().getEstado() != StatusHandlerEnum.ACTIVO.getValue()) {
    // throw new GenericException("El usuario no se encuentra activo",
    // CoreUtilConstants.INFORMATIVE_VALUES);
    // }
    if (request.getUsrCreacion() == null) {
      throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getFeCreacion() == null) {
      throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getEstado() == null) {
      throw new GenericException("El valor estado es requerido", CoreUtilConstants.MISSING_VALUES);
    }
  }

  // public User buscarPorUsername(String username) throws GenericException {
  // if (username.isEmpty()) {
  // throw new GenericException("El valor username es no debe estar vacío",
  // CoreUtilConstants.MISSING_VALUES);
  // }
  // User response = postRepository.findByUsername(username);
  // if (response == null) {
  // throw new GenericException("El usuario " + username + " no existe",
  // CoreUtilConstants.INFORMATIVE_VALUES);
  // }
  // return response;
  // }

  // public User buscarPorEmail(String email) throws GenericException {
  // if (email.isEmpty()) {
  // throw new GenericException("El valor username es no debe estar vacío",
  // CoreUtilConstants.MISSING_VALUES);
  // }
  // User response = postRepository.findByUsername(email);
  // if (response == null) {
  // throw new GenericException("El usuario " + email + " no existe",
  // CoreUtilConstants.INFORMATIVE_VALUES);
  // }
  // return response;
  // }

  public Post validarActualizar(Post request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!postRepository.existsById(request.getId())) {
      throw new GenericException("El post no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    if (request.getFeModificacion() == null) {
      throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrModificacion() == null) {
      throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    Optional<Post> optionalUsuario = postRepository.findById(request.getId());
    Post response = optionalUsuario.orElse(request);
    response.setTitulo(request.getTitulo() != null ? request.getTitulo() : response.getTitulo());
    response.setContenido(request.getContenido() != null ? request.getContenido() : response.getContenido());
    response.setUsrCreacion(request.getUsrCreacion() != null ? request.getUsrCreacion() : response.getUsrCreacion());
    response.setFeCreacion(request.getFeCreacion() != null ? request.getFeCreacion() : response.getFeCreacion());
    response.setUsrModificacion(
        request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
    response.setFeModificacion(
        request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    return response;
  }
}
