package com.consulti.apiconsulti.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "LABEL", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class Label {
  @Id
  @GeneratedValue()
  @Column(name = "id", unique = true, updatable = false)
  private UUID id;
  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "tipo", nullable = false)
  private String tipo;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
  @Column(name = "fecha_creacion")
  private Date feCreacion;

  @Column(name = "user_creacion")
  private String usrCreacion;

  @Column(name = "estado")
  private String estado;

  public static final String ID_LABEL = "id";
}
