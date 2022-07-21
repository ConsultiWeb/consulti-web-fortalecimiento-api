package com.consulti.apiconsulti.utils.validations;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.File;
import com.consulti.apiconsulti.entity.Postulacion;
import com.consulti.apiconsulti.entity.Resume;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.repository.FileRepository;
import com.consulti.apiconsulti.repository.PostulacionRepository;
import com.consulti.apiconsulti.repository.ResumeRepository;

import com.consulti.apiconsulti.repository.FileRepository;

import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import org.apache.commons.io.FilenameUtils;


@Component
public class FileValidations {

  @Autowired
  private PostulacionRepository postulacionRepository;

  public Resume existeHojaVida(String id)  throws GenericException {
    Optional<Postulacion> optionalPostulacion   = postulacionRepository.findById(UUID.fromString(id));
    if(!optionalPostulacion.isPresent()){
      throw new GenericException("Sin hoja de vida");
    }
    Postulacion pt = optionalPostulacion.get();
    Resume re = pt.getResumen();
    if( re.getMultimediaId()==null) {
      throw new GenericException("Sin hoja de vida");
    }
    return re;

  }
  public File validarHojaVida(MultipartFile request) throws GenericException {
    File response = new File();
    try {
      if(request.isEmpty()||request==null){
        throw new GenericException("Hoja de vida requerida", CoreUtilConstants.EXISTING_VALUES);
      }
      if(!FilenameUtils.getExtension(request.getOriginalFilename()).equals("pdf")){
        throw new GenericException("Sólo se admite la hoja de vida en formato pdf", CoreUtilConstants.EXISTING_VALUES);
      }
      response.setType(request.getContentType());
      response.setData(request.getBytes());
    } catch (IOException e) {
      throw new GenericException("Archivo inválido", CoreUtilConstants.EXISTING_VALUES);
    }
    return response;
  }
  public File validarArchivo(MultipartFile request) throws GenericException {
    File response = new File();
    try {
      response.setType(request.getContentType());
      response.setData(request.getBytes());
    } catch (IOException e) {
      throw new GenericException("Archivo inválido", CoreUtilConstants.EXISTING_VALUES);
    }
    return response;
  }

  public void validarVacio(File request) throws GenericException {
    File objNull = new File();
    if (request.equals(objNull)) {
      throw new GenericException("Se deben declarar variables para buscar el archivo",
          CoreUtilConstants.MISSING_VALUES);
    }
  }

  @Autowired
  private FileRepository fileRepository;

  public File validarActualizar(File request) throws GenericException {
    if (request.getId() == null) {
      throw new GenericException("El valor id es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (!fileRepository.existsById(request.getId())) {
      throw new GenericException("El archivo no existe", CoreUtilConstants.EXISTING_VALUES);
    }
    if (request.getFeModificacion() == null) {
      throw new GenericException("El valor fechaModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    if (request.getUsrModificacion() == null) {
      throw new GenericException("El valor userModificacion es requerido", CoreUtilConstants.MISSING_VALUES);
    }
    Optional<File> optionalLabel = fileRepository.findById(request.getId());
    File response = optionalLabel.orElse(request);
    response.setName(request.getName() != null ? request.getName() : response.getName());
    response.setData(request.getData() != null ? request.getData() : response.getData());
    response.setType(request.getType() != null ? request.getType() : response.getType());
    response.setFeModificacion(
        request.getFeModificacion() != null ? request.getFeModificacion() : response.getFeModificacion());
    response.setUsrModificacion(
        request.getUsrModificacion() != null ? request.getUsrModificacion() : response.getUsrModificacion());
    response.setEstado(request.getEstado() != null ? request.getEstado() : response.getEstado());
    return response;
  }
}
