package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.File;
import com.consulti.apiconsulti.entity.HistoricoContacto;
import com.consulti.apiconsulti.service.FileService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.springframework.http.HttpHeaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

@RestController()
// @RequestMapping({ ConsultiConstants.FILE_URL })
public class FileController {
  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  private FileService storageService;


  @PostMapping(path = { ConsultiConstants.UPLOAD }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<File> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FILE_URL, ConsultiConstants.UPLOAD);
    GenericBasicResponse<File> response = new GenericBasicResponse<>();
    response.setData(storageService.store(file));
    return response;
  }

  @PostMapping(path = "/updateFile", produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<File> updateFile(@RequestParam("file") MultipartFile file,
      @RequestParam("request") Object request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FILE_URL, "/updateFile");
    GenericBasicResponse<File> response = new GenericBasicResponse<>();
    File parsedReq = GeneralUtils.mapearObjDeserializado(request, File.class);
    response.setData(storageService.actualizar(parsedReq, file));
    return response;
  }
  @PostMapping("/import_excel")
  public GenericListResponse<HistoricoContacto> importExcelFile(@RequestParam("file") MultipartFile files,
                                                                @RequestParam("usrCreacion") String usrCreacion) throws Exception {
    GenericListResponse<HistoricoContacto> response = new GenericListResponse<>();
    response.setData(storageService.importarExcel(files,usrCreacion));
    return response;
  }
  @PostMapping(path = { "/profile_image" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<File> listarPor(@RequestParam("file") MultipartFile file,
                                              @RequestParam("id") String id) throws Exception {
    GenericBasicResponse<File> response = new GenericBasicResponse<>();
    response.setData(storageService.storeProfileImage(id,file));
    return response;
  }
  @PostMapping(path = { "/upload_cv" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<File> subirCv(@RequestParam("file") MultipartFile file,
                                              @RequestParam("id") String id) throws Exception {
    GenericBasicResponse<File> response = new GenericBasicResponse<>();
    response.setData(storageService.storeCv(id,file));
    return response;
  }
  @GetMapping(path = { "/profile_image" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public  ResponseEntity<byte[]>  obtenerImagen(@RequestParam("id") String id) throws Exception {
    GenericBasicResponse<File> response = new GenericBasicResponse<>();
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + storageService.obtenerProfileImage(id).getName() + "\"")
            .contentType(MediaType.parseMediaType(storageService.obtenerProfileImage(id).getType()))
            .body(storageService.obtenerProfileImage(id).getData());
  }
  @GetMapping(path = { "/get_cv" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public  ResponseEntity<byte[]>  obtenerCV(@RequestParam("id") String id) throws Exception {
    GenericBasicResponse<File> response = new GenericBasicResponse<>();

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + storageService.obtenerCV(id).getName() + "\"")
            .contentType(MediaType.parseMediaType(storageService.obtenerCV(id).getType()))
            .body(storageService.obtenerCV(id).getData());
  }

  @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericListResponse<File> listarPor(@RequestBody File request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FILE_URL, ConsultiConstants.LISTA_POR);
    GenericListResponse<File> response = new GenericListResponse<>();
    response.setData(storageService.listarPor(request));
    return response;
  }

  @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericBasicResponse<Boolean> eliminar(@RequestBody File request) throws Exception {
    log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.FILE_URL, ConsultiConstants.ELIMINAR);
    GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
    response.setData(storageService.eliminar(request));
    return response;
  }
}
