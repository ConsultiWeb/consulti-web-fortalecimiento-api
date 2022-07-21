package com.consulti.apiconsulti.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.HistoricoPostulacion;
import com.consulti.apiconsulti.entity.Postulacion;
import com.consulti.apiconsulti.entity.Resume;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.model.MailPostulacionReqDTO;
import com.consulti.apiconsulti.model.PostulacionProcesoReclutamientoDTO;
import com.consulti.apiconsulti.model.PostulacionReqDTO;
import com.consulti.apiconsulti.repository.HistoricoPostulacionRepository;
import com.consulti.apiconsulti.repository.PostulacionRepository;
import com.consulti.apiconsulti.service.FileService;
import com.consulti.apiconsulti.service.MailService;
import com.consulti.apiconsulti.service.PostulacionService;
import com.consulti.apiconsulti.service.ResumeService;
import com.consulti.apiconsulti.service.UserService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.PostulacionValidations;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PostulacionServiceImpl implements PostulacionService {
  Logger log = LogManager.getLogger(this.getClass());

  @Autowired
  PostulacionRepository postulacionRepo;

  @Autowired
  HistoricoPostulacionRepository historicoPostulacionRepo;

  @Autowired
  PostulacionValidations postulacionValidations;

  @Autowired
  MailService mailService;

  @Autowired
  UserService userService;

  @Autowired
  ResumeService resumeService;

  @Autowired
  FileService fileService;

  @Value("${mail.de}")
  String noReplyMail;

  @Value("${url.invitation}")
  String urlInvitation;

  @Override
  public List<Postulacion> listar() throws GenericException {
    List<Postulacion> response;
    try {
      GeneralUtils.validarMaximoDataLista(postulacionRepo.count());
      response = postulacionRepo.findAll();
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public List<Postulacion> listarPor(Postulacion request) throws GenericException {
    List<Postulacion> response;
    try {
      postulacionValidations.validarVacio(request);
      Example<Postulacion> listaFiltros = Example.of(request);
      response = postulacionRepo.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, Postulacion.ID_POSTULACION));
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Postulacion guardar(PostulacionReqDTO request) throws GenericException {
    Postulacion response;
    User userReq = new User();
    try {
      request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      Postulacion postulacionReq = GeneralUtils.mapearObjDeserializado(request, Postulacion.class);
      Resume resumeReq = GeneralUtils.mapearObjDeserializado(request, Resume.class);
      userReq.setTelefono(request.getTelefono());
      userReq.setId(request.getUserId());
      userReq.setEstado(request.getEstado());
      userReq.setUsrModificacion(request.getUsrCreacion());
      postulacionReq.setUser(userService.actualizar(userReq));
      Resume resume = resumeService.guardar(resumeReq);
      postulacionReq.setResumen(resume);
      postulacionReq.setFeCreacion(new Date());
      postulacionValidations.validarGuardar(postulacionReq);
      response = postulacionRepo.save(postulacionReq);



    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Postulacion actualizar(PostulacionReqDTO request) throws GenericException {
    Postulacion response = new Postulacion();
    User userReq = new User();
    try {
      Postulacion postulacionReq = GeneralUtils.mapearObjDeserializado(request, Postulacion.class);
      if (request.getResumenId() != null) {
        Resume resumeReq = GeneralUtils.mapearObjDeserializado(request, Resume.class);
        resumeReq.setId(request.getResumenId());
        postulacionReq.setResumen(resumeService.actualizar(resumeReq));
      }
      if (request.getUserId() != null) {
        userReq.setTelefono(request.getTelefono());
        userReq.setId(request.getUserId());
        userReq.setUsrModificacion(request.getUsrModificacion());
        userReq.setEstado(request.getEstado());
        postulacionReq.setUser(userService.actualizar(userReq));
      }
      postulacionReq.setFeModificacion(new Date());
      postulacionReq = postulacionValidations.validarActualizar(postulacionReq);
      response = postulacionRepo.save(postulacionReq);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
   /* Funcion que relaciona un proceso de reclutamiento con la postulacion actuak
        @param: PostulacionProcesoReclutamientoDTO request
        @return: Postulacion
    */
  @Override
  public Postulacion postular(PostulacionProcesoReclutamientoDTO request) throws GenericException {
    Postulacion response = new Postulacion();
    User userReq = new User();
    try {
      Postulacion postulacionReq = GeneralUtils.mapearObjDeserializado(request, Postulacion.class);
      Resume consulta = new Resume();
      User userConsulta = new User();
      userConsulta.setId(request.getUserId());
      consulta.setUser(userConsulta);
      List<Resume> list =  resumeService.listarPor(consulta);
      if (list.size()== 0) {
        throw new GenericException("Primero debe actualizar su perfil", CoreUtilConstants.MISSING_VALUES);
      }
      postulacionReq.setResumen(list.get(0));
      userReq.setId(request.getUserId());
      postulacionReq.setUser(userReq);
      postulacionReq.setFeModificacion(new Date());
      postulacionReq.setProcesoReclutamientoId(request.getProcesoReclutamientoId());
      postulacionReq.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      postulacionReq.setFeCreacion(new Date());
      postulacionReq.setUsrCreacion(request.getUsrCreacion());
      response = postulacionRepo.save(postulacionReq);

      //GUARDADO EN HISTORIAL
      HistoricoPostulacion post = new HistoricoPostulacion();
      post.setFeCreacion(new Date());
      post.setResumenId(list.get(0).getId().toString());
      post.setUserId(request.getUserId().toString());
      historicoPostulacionRepo.save(post);

    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Boolean eliminar(Postulacion request) throws GenericException {
    Boolean response = false;
    try {
      request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
      request.setFeModificacion(new Date());
      request = postulacionValidations.validarEliminar(request);
      postulacionRepo.saveAndFlush(request);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public Boolean enviarInvitacionTeorico(MailPostulacionReqDTO request, MultipartFile file) throws GenericException {
    Boolean response = false;
    try {
      String outputFileName = "invitacion-" + request.getPara() + ".pdf";
      File fileWritten = new File(outputFileName);
      FileUtils.writeByteArrayToFile(fileWritten, fileService.store(file).getData());
      request.setRutaArchivo(outputFileName);
      request.setDe(noReplyMail);
      mailService.sendMessageWithAttachment(request);
      FileUtils.forceDelete(fileWritten);
      response = true;
      if (response) {
        PostulacionReqDTO postulacionReq = new PostulacionReqDTO();
        postulacionReq.setId(request.getPostulacionId());
        postulacionReq.setEstado(StatusHandlerEnum.INVITADO.getValue());
        postulacionReq.setUsrModificacion(request.getUsername());
        actualizar(postulacionReq);
      }
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    } catch (IOException e) {
      throw new GenericException(e.getMessage(), CoreUtilConstants.ERROR_PARSE_VALUES);
    }
    return response;
  }

  @Override
  public Boolean enviarHojaDeVida(MailPostulacionReqDTO request, MultipartFile file) throws GenericException {
    Boolean response = false;
    try {
      String outputFileName = file.getOriginalFilename() + "-hoja-de-vida" + ".pdf";
      File fileWritten = new File(outputFileName);
      FileUtils.writeByteArrayToFile(fileWritten, fileService.store(file).getData());
      request.setRutaArchivo(outputFileName);
      request.setDe(noReplyMail);
      mailService.sendMessageWithAttachment(request);
      FileUtils.forceDelete(fileWritten);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    } catch (IOException e) {
      throw new GenericException(e.getMessage(), CoreUtilConstants.ERROR_PARSE_VALUES);
    }
    return response;
  }

  @Override
  public Boolean enviarMultipleHojaDeVida(MailPostulacionReqDTO request, MultipartFile[] files)
      throws GenericException {
    Boolean response = false;
    try {
      List<String> outputFilesNames = new ArrayList<>();
      List<File> filesWritten = new ArrayList<>();
      List<MultipartFile> filesList = Arrays.asList(files);
      for (MultipartFile file : filesList) {
        String outputFileName = file.getOriginalFilename() + "-hoja-de-vida" + ".pdf";
        File fileWritten = new File(outputFileName);
        FileUtils.writeByteArrayToFile(fileWritten, fileService.store(file).getData());
        outputFilesNames.add(outputFileName);
        filesWritten.add(fileWritten);
      }
      request.setRutasListaArchivos(outputFilesNames);
      request.setDe(noReplyMail);
      
      mailService.sendMessageWithMultipleAttachments(request);
      for (File fileWritten : filesWritten) {
        FileUtils.forceDelete(fileWritten);
      }
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    } catch (IOException e) {
      throw new GenericException(e.getMessage(), CoreUtilConstants.ERROR_PARSE_VALUES);
    }
    return response;
  }
}
