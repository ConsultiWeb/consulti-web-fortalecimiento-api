package com.consulti.apiconsulti.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import com.consulti.apiconsulti.entity.HistoricoContacto;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

  File store(MultipartFile file) throws GenericException;

  File getFile(UUID id);

  Stream<File> getAllFiles();

  List<File> listar() throws GenericException;
  File obtenerProfileImage(String id);
  File storeProfileImage(String id,MultipartFile file) throws GenericException;
  File storeCv(String id,MultipartFile file) throws GenericException;
  File obtenerCV(String id) throws  GenericException;

  List<File> listarPor(File request) throws GenericException;

  Boolean eliminar(File request) throws GenericException;

  File actualizar(File request, MultipartFile file) throws GenericException;
  List<HistoricoContacto> importarExcel(MultipartFile file,String usrCreacion) throws  Exception;
}
