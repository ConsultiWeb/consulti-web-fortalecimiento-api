package com.consulti.apiconsulti.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "DETALLE_RESUME", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class DetalleResume {
  @Id
  @GeneratedValue()
  @Column(name = "id", unique = true, updatable = false)
  private UUID id;

  @Column(name = "resume_id", unique = true, updatable = false)
  private UUID resumeId;

  @Column(name = "detalle_nombre")
  private String nombre;

  @Column(name = "detalle_valor")
  private String valor;

  @Column(name = "detalle_tipo")
  private String tipo;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
  @Column(name = "fecha_creacion")
  private Date feCreacion;

  @Column(name = "user_creacion")
  private String usrCreacion;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
  @Column(name = "fecha_modificacion")
  private Date feModificacion;

  @Column(name = "user_modificacion")
  private String usrModificacion;

  @Column(name = "estado")
  private String estado;

  public final static String ID_DETALLE_RESUME = "id";
}
