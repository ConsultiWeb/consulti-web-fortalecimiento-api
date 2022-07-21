package com.consulti.apiconsulti.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.*;
import com.consulti.apiconsulti.repository.UserRepository;
import com.consulti.apiconsulti.repository.PostulacionRepository;
import com.consulti.apiconsulti.repository.FileRepository;
import com.consulti.apiconsulti.repository.ResumeRepository;
import com.consulti.apiconsulti.service.CiudadService;
import com.consulti.apiconsulti.service.FileService;
import com.consulti.apiconsulti.service.HistoricoContactoService;
import com.consulti.apiconsulti.service.MailService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.FileValidations;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

  @Autowired
  private FileRepository fileRepository;
  @Autowired
  private ResumeRepository resumeRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostulacionRepository postulacionRepository;

  @Autowired
  private FileValidations fileValidations;
  @Autowired
  private MailService mailService;

  @Autowired
  private CiudadService ciudadService;

  @Autowired
  private HistoricoContactoService historicoContactoService;

  @Value("${mail.para}")
  String paraMail;

  @Value("${mail.de}")
  String noReplyMail;

  public File store(MultipartFile request) throws GenericException {
    File response = new File();
    try {
      String fileName = StringUtils.cleanPath(request.getOriginalFilename());
      File file = fileValidations.validarArchivo(request);
      file.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      file.setFeCreacion(new Date());
      file.setName(fileName);
      response = fileRepository.save(file);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  /* Funcion que guarda una imagen de perfil y la relaciona a un id de usuario
        @param: String id,MultipartFile request
        @return: File
    */
  public File storeProfileImage(String id,MultipartFile request) throws GenericException {
    File response = new File();
    try {
      String fileName = StringUtils.cleanPath(request.getOriginalFilename());
      File file = fileValidations.validarArchivo(request);
      file.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      file.setFeCreacion(new Date());
      file.setName(fileName);
      File saving = fileRepository.save(file);
      //UPDATE USER
      Optional<User> optionalLabel   = userRepository.findById(UUID.fromString(id));
      User res = optionalLabel.orElse(null);
      res.setMultimediaId(saving.getId());
      userRepository.save(res);

    } catch (GenericException e) {
    throw new GenericException(e.getMessageError(), e.getCodeError());
  }
    return response;
  }
   /* Funcion que guarda un archivo CV en formato pdf y la relaciona a un id de usuario
        @param: String id,MultipartFile request
        @return: File
    */
  public File storeCv(String id,MultipartFile request) throws GenericException {
    File response = new File();
    try {
      String fileName = StringUtils.cleanPath(request.getOriginalFilename());
      File file = fileValidations.validarHojaVida(request);
      file.setEstado(StatusHandlerEnum.ACTIVO.getValue());
      file.setFeCreacion(new Date());
      file.setName(fileName);
      File saving = fileRepository.save(file);
      //UPDATE RESUME
      Optional<Resume> optionalResume  = resumeRepository.findById(UUID.fromString(id));
      Resume resu = optionalResume.orElse(null);
      resu.setMultimediaId(saving.getId());
      resumeRepository.save(resu);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
  /* Funcion que lee un archivo excel importado y registra los contactos en la base de datos
        @param: MultipartFile request,String usrCreacion
        @return: List<HistoricoContacto>
    */
  public List<HistoricoContacto> importarExcel(MultipartFile request,String usrCreacion) throws Exception {
    List<HistoricoContacto> users = new ArrayList<>();
    try {
      XSSFWorkbook workbook = new XSSFWorkbook(request.getInputStream());
      XSSFSheet worksheet = workbook.getSheetAt(0);
      ArrayList<String> correosAEnviar = new ArrayList<>();
      for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
        if (index > 0) {
          XSSFRow row = worksheet.getRow(index);
          HistoricoContacto contact = new HistoricoContacto();
          contact.setNombres(getCellValue(row, 0));
          contact.setApellidos(getCellValue(row, 1));
          Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(getCellValue(row, 2));
          contact.setFeNacimiento(date1);
          contact.setGenero(getCellValue(row, 3));
          Ciudad ciu = new Ciudad();
          ciu.setNombre(getCellValue(row, 4).toUpperCase());
          List<Ciudad> ciuList = ciudadService.listarPor(ciu);
          if(!ciuList.isEmpty()){
            contact.setCiudadId(ciuList.get(0).getId());
          }
          contact.setTelefono(getCellValue(row, 5));
          contact.setEmail(getCellValue(row, 6));
          contact.setIdentificacion(getCellValue(row, 7));
          contact.setUsrCreacion(usrCreacion);
          contact.setFeCreacion(new Date());
          historicoContactoService.guardar(contact);
          users.add(contact);
          correosAEnviar.add(getCellValue(row, 6));
        }
    }
    mailService.sendWithCCOnly(noReplyMail,paraMail,correosAEnviar);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    return users;
  }
  /* Funcion que obtiene el valor de la celda del archivo excel
        @param: Row row, int cellNo
        @return: String
  */
  private String getCellValue(Row row, int cellNo) {
    DataFormatter formatter = new DataFormatter();
    Cell cell = row.getCell(cellNo);
    return formatter.formatCellValue(cell);
  }
  /* Funcion que obtiene el cv en formato pdf BLOB del usuario
        @param: String id
        @return: File
  */
  public File obtenerCV(String id)  throws GenericException{
    Optional<Postulacion> ptOptional = postulacionRepository.findById(UUID.fromString(id));
    if(ptOptional.isPresent()){
      Postulacion pt = ptOptional.get();
      Resume re = pt.getResumen();

      if( re.getMultimediaId()!=null){
        return  fileRepository.findById( re.getMultimediaId()).get();
      }else{
        throw new GenericException("No se ha encontrado un cv", CoreUtilConstants.EXISTING_VALUES);
      }
    }else{
      throw new GenericException("No se ha encontrado la postulacion", CoreUtilConstants.EXISTING_VALUES);
    }
  }
  /* Funcion que obtiene la foto de perfil en formato BLOB del usuario
        @param: String id
        @return: File
  */
  public File obtenerProfileImage(String id){
    File nuevo = new File();
    Optional<User> usrOptional = userRepository.findById(UUID.fromString(id));
    if(usrOptional.isPresent()){
      User user = usrOptional.get();
      if(user.getMultimediaId()!=null){
        return  fileRepository.findById( user.getMultimediaId()).orElse(nuevo);
      }else{
        return nuevo;
      }
    }else{
        return nuevo;
    }

  }
  public File getFile(UUID id) {
    File response = new File();
    return fileRepository.findById(id).orElse(response);
  }

  public Stream<File> getAllFiles() {
    return fileRepository.findAll().stream();
  }


  public List<File> listar() throws GenericException {
    List<File> response;
    try {
      GeneralUtils.validarMaximoDataLista(fileRepository.count());
      response = fileRepository.findAll();
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  @Override
  public List<File> listarPor(File request) throws GenericException {
    List<File> response;
    try {
      fileValidations.validarVacio(request);
      Example<File> listaFiltros = Example.of(request);
      response = fileRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, File.ID_FILE));
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  public Boolean eliminar(File request) throws GenericException {
    Boolean response = false;
    try {
      request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
      request.setFeModificacion(new Date());
      request = fileValidations.validarActualizar(request);
      fileRepository.saveAndFlush(request);
      response = true;
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }

  public File actualizar(File reqFile, MultipartFile request) throws GenericException {
    File response = new File();
    try {
      String fileName = StringUtils.cleanPath(request.getOriginalFilename());
      File file = fileValidations.validarArchivo(request);
      file.setId(reqFile.getId());
      file.setFeModificacion(new Date());
      file.setUsrModificacion(reqFile.getUsrModificacion());
      file.setName(fileName);
      file = fileValidations.validarActualizar(file);
      response = fileRepository.save(file);
    } catch (GenericException e) {
      throw new GenericException(e.getMessageError(), e.getCodeError());
    }
    return response;
  }
}
