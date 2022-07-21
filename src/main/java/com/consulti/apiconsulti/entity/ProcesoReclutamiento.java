
package com.consulti.apiconsulti.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "PROCESO_RECLUTAMIENTO", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class ProcesoReclutamiento {
    @Id
    @GeneratedValue()
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;

    @Column(name = "nombre")
    private String nombre;

    @OneToOne(targetEntity = Cargo.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Column(name = "esta_publicado")
    private String publicado;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
    @Column(name = "fecha_inicio")
    private Date feInicio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
    @Column(name = "fecha_fin")
    private Date feFin;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
    @Column(name = "fecha_creacion")
    private Date feCreacion;

    @Column(name = "user_creacion")
    private String usrCreacion;

    @Column(name = "user_modificacion")
    private String usrModificacion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
    @Column(name = "fecha_modificacion")
    private Date feModificacion;

    @Column(name = "estado")
    private String estado;

    public static final String ID_PROCESO_RECLUTAMIENTO = "id";
}
