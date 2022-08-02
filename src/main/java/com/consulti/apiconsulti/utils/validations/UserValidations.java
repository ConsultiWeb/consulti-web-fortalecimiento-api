package com.consulti.apiconsulti.utils.validations;

import java.util.Optional;
//import java.util.ArrayList;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.ProcesoReclutamiento;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.repository.ProcesoReclutamientoRepository;
import com.consulti.apiconsulti.repository.UserRepository;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserValidations {

  @Autowired
  UserRepository userRepository;

  @Autowired
  ProcesoReclutamientoRepository procesoReclutamientoRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  public void validarUsuarioVacio(User request) throws GenericException {
    User objNull = new User();
    if (request.equals(objNull)) {
      throw new GenericException("Se deben declarar variables para buscar el usuario",
          CoreUtilConstants.MISSING_VALUES);
    }
  }

  public void validarGuardar(User request) throws GenericException {
    if (procesoReclutamientoRepository.countByEstado("Activo")==0){
      throw new GenericException("No hay un proceso de reclutamiento activo", CoreUtilConstants.MISSING_VALUES);
    }
//    ArrayList<ProcesoReclutamiento> lista=procesoReclutamientoRepository.findAll();
//    for (int i=0; i<=lista.length; i++){
//        if (lista[i].feFin<)
//    }
//    if (procesoReclutamientoRepository.findAll())
    if (request.getIdentificacion() == null|| request.getIdentificacion().length() == 0) {
      throw new GenericException("El valor identificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if(GeneralUtils.validarCedula(request.getIdentificacion())==false){
      throw new GenericException("Ingrese una cédula válida", CoreUtilConstants.INFORMATIVE_VALUES);
    }
    if (request.getEmail() == null|| request.getEmail().length() == 0) {
      throw new GenericException("El valor email es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsername() == null|| request.getUsername().length() == 0) {
      throw new GenericException("El valor userName es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getNombres() == null|| request.getNombres().length() == 0) {
      throw new GenericException("El valor nombres es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getApellidos() == null|| request.getApellidos().length() == 0) {
      throw new GenericException("El valor apellidos es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getPassword() == null|| request.getPassword().length() == 0) {
      throw new GenericException("El valor password es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getFeNacimiento() == null) {
      throw new GenericException("El valor feNacimiento es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getTipo() == null|| request.getTipo().length() == 0) {
      throw new GenericException("El valor tipo es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrCreacion() == null) {
      throw new GenericException("El valor usrCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getFeCreacion() == null) {
      throw new GenericException("El valor feCreacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getEstado() == null) {
      throw new GenericException("El valor estado es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (userRepository.existsByUsername(request.getUsername())) {
    throw new GenericException("El usuario " + request.getUsername() + " no se encuentra disponible", CoreUtilConstants.INFORMATIVE_VALUES);
    }
     if (userRepository.existsByEmail(request.getEmail()))
    {
     throw new GenericException("El correo " + request.getEmail() + " no se encuentra disponible",CoreUtilConstants.INFORMATIVE_VALUES);
     }
    if (userRepository.existsByIdentificacion(request.getIdentificacion())) {
    throw new GenericException("La identificación " + request.getIdentificacion() + " ya existe",CoreUtilConstants.INFORMATIVE_VALUES);
     }
  }

  public User buscarPorUsername(String username) throws GenericException {
    if (username.isEmpty()) {
      throw new GenericException("El valor username es no debe estar vacío", CoreUtilConstants.MISSING_VALUES);
    }
    User response = userRepository.findByUsername(username);
    if (response == null) {
      throw new GenericException("El usuario " + username + " no existe", CoreUtilConstants.INFORMATIVE_VALUES);
    }
    return response;
  }

  public User buscarPorEmail(String email) throws GenericException {
    if (email.isEmpty()) {
      throw new GenericException("El valor username es no debe estar vacío", CoreUtilConstants.MISSING_VALUES);
    }
    User response = userRepository.findByUsername(email);
    if (response == null) {
      throw new GenericException("El usuario " + email + " no existe", CoreUtilConstants.INFORMATIVE_VALUES);
    }
    return response;
  }

  public User validarActualizar(User request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!userRepository.existsById(request.getId())) {
      throw new GenericException("El usuario no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    if (request.getPassword() != null && !request.getPassword().isEmpty()) {
      request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
    }
    // if (request.getIdentificacion() != null) {
    // throw new GenericException("El valor identificacion no es modificable",
    // CoreUtilConstants.EXISTING_VALUES);
    // }
    // if (request.getNombres().isEmpty()) {
    // throw new GenericException("El valor nombres no es modificable",
    // CoreUtilConstants.EXISTING_VALUES);
    // }
    // if (request.getApellidos().isEmpty()) {
    // throw new GenericException("El valor nombres no es modificable",
    // CoreUtilConstants.EXISTING_VALUES);
    // }
    // if (request.getUsername() != null) {
    // throw new GenericException("El valor username no es modificable",
    // CoreUtilConstants.EXISTING_VALUES);
    // }
    if (request.getFeModificacion() == null) {
      throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrModificacion() == null) {
      throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    // if (!userRepository.existsByIdAndEmail(request.getId(), request.getEmail()))
    // {
    // throw new GenericException("El correo " + request.getEmail() + "no se
    // encuentra disponible",
    // CoreUtilConstants.INFORMATIVE_VALUES);
    // }

    Optional<User> optionalUsuario = userRepository.findById(request.getId());
    User response = optionalUsuario.orElse(request);
    response.setEmail(request.getEmail() != null ? request.getEmail() : response.getEmail());
    response.setNombres(request.getNombres() != null ? request.getNombres() : response.getNombres());
    response.setApellidos(request.getApellidos() != null ? request.getApellidos() : response.getApellidos());
    response.setGenero(request.getGenero() != null ? request.getGenero() : response.getGenero());
    response.setPassword(request.getPassword() != null ? request.getPassword() : response.getPassword());
    response.setTipo(request.getTipo() != null ? request.getTipo() : response.getTipo());
    response.setTelefono(request.getTelefono() != null ? request.getTelefono() : response.getTelefono());
    response
        .setFeNacimiento(request.getFeNacimiento() != null ? request.getFeNacimiento() : response.getFeNacimiento());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    response.setFeModificacion(
        request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
    response.setUsrModificacion(
        request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
    return response;
  }
}
