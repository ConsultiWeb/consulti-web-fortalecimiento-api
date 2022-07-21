package com.consulti.apiconsulti.model;

import java.util.List;
import java.util.UUID;

import com.consulti.apiconsulti.entity.DetalleResume;
import com.consulti.apiconsulti.entity.Label;

import lombok.Data;

@Data
public class PostulacionReqDTO {
  private UUID id;
  private UUID userId;
  private UUID resumenId;
  private String telefono;
  private String urlPortafolio;
  private String urlExtra;
  private String descripcion;
  private String usrCreacion;
  private String usrModificacion;
  private List<Label> tecnologias;
  private List<DetalleResume> detalles;
  private String estado;
}
