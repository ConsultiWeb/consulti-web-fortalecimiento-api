
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
@Table(name = "INFORMACION_CONTACTO", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class InformacionContacto {
    @Id
    @GeneratedValue()
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;

    @Column(name = "user_id", unique = true, updatable = false)
    private UUID userId;
    @Column(name = "tipo_contacto_id", unique = true, updatable = false)
    private UUID tipoContactoId;

    @Column(name = "valor")
    private String valor;

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

    public static final String ID_INFORMACION_CONTACTO = "id";
}
