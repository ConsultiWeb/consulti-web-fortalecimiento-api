
package com.consulti.apiconsulti.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "CARGO", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class Cargo {
    @Id
    @GeneratedValue()
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;

    @Column(name = "nombre")
    private String nombre;

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

    public static final String ID_CARGO = "id";
}
