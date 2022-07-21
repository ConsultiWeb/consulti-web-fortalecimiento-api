package com.consulti.apiconsulti.service;

import java.util.List;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Postulacion;
import com.consulti.apiconsulti.model.MailPostulacionReqDTO;
import com.consulti.apiconsulti.model.PostulacionProcesoReclutamientoDTO;
import com.consulti.apiconsulti.model.PostulacionReqDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PostulacionService {
  public List<Postulacion> listar() throws GenericException;

  public List<Postulacion> listarPor(Postulacion request) throws GenericException;

  public Postulacion guardar(PostulacionReqDTO request) throws GenericException;

  public Postulacion actualizar(PostulacionReqDTO request) throws GenericException;

  public Boolean eliminar(Postulacion request) throws GenericException;

  public Boolean enviarInvitacionTeorico(MailPostulacionReqDTO request, MultipartFile file) throws GenericException;

  public Boolean enviarHojaDeVida(MailPostulacionReqDTO request, MultipartFile file) throws GenericException;

  public Boolean enviarMultipleHojaDeVida(MailPostulacionReqDTO request, MultipartFile[] file) throws GenericException;
  public Postulacion postular(PostulacionProcesoReclutamientoDTO request) throws GenericException;


}
