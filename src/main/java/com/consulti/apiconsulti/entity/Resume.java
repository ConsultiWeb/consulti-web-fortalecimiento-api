package com.consulti.apiconsulti.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "RESUME", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class Resume {
  @Id
  @GeneratedValue()
  @Column(name = "id", unique = true, updatable = false)
  private UUID id;

  @Column(name = "url_resume")
  private String urlPortafolio;

  @Column(name = "url_extra")
  private String urlExtra;

  @Column(name = "descripcion")
  private String descripcion;

  @Column(name = "anios_experiencia")
  private int aniosExperiencia;

  @Column(name = "multimedia_id")
  private UUID multimediaId;

  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @OneToMany
  @JoinColumn(name = "resume_id", referencedColumnName = "ID")
  private List<DetalleResume> detalles = new ArrayList<>();

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
  @Column(name = "fecha_creacion")
  private Date feCreacion;

  @Column(name = "user_creacion")
  private String usrCreacion;

  @Column(name = "user_modificacion")
  private String usrModificacion;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
  @Column(name = "fecha_modificacion")
  private Date feModificacion;

  @Column(name = "estado")
  private String estado;

  @ManyToMany(cascade = CascadeType.MERGE)
  @JoinTable(name = "consulti.resume_label", joinColumns = {
          @JoinColumn(name = "resume_id", nullable = false) }, inverseJoinColumns = {
          @JoinColumn(name = "label_id", nullable = false) })
  private List<Label> tecnologias = new ArrayList<>();

  public final static String ID_RESUME = "id";
}
