
package com.consulti.apiconsulti.entity;

import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "ACTIVIDAD", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class Actividad {
    @Id
    @GeneratedValue()
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "modulo")
    private String modulo;

    @Column(name = "ip")
    private String ip;

    @Column(name = "equipo")
    private String equipo;

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

    public static final String ID_ACTIVIDAD = "id";
}
