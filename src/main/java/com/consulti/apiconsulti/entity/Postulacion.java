package com.consulti.apiconsulti.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "POSTULACION", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class Postulacion {
  @Id
  @GeneratedValue()
  @Column(name = "ID", unique = true, updatable = false)
  private UUID id;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
  @Column(name = "fecha_creacion")
  private Date feCreacion;

  @Column(name = "user_modificacion")
  private String usrModificacion;

  @Column(name = "user_creacion")
  private String usrCreacion;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
  @Column(name = "fecha_modificacion")
  private Date feModificacion;

  @OneToOne(targetEntity = Resume.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(updatable = true, nullable = false, name = "resume_id")
  private Resume resumen;

  @Column(name = "proceso_reclutamiento_id")
  private UUID procesoReclutamientoId;

  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @ManyToMany(cascade = CascadeType.MERGE)
  @JoinTable(name = "consulti.postulacion_label", joinColumns = {
      @JoinColumn(name = "postulacion_id", nullable = false) }, inverseJoinColumns = {
          @JoinColumn(name = "label_id", nullable = false) })
  private List<Label> tecnologias = new ArrayList<>();

  @Column(name = "ESTADO")
  private String estado;

  public final static String ID_POSTULACION = "id";
}
