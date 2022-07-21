package com.consulti.apiconsulti.entity;

import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "HISTORICO_POSTULACION", schema = ConsultiConstants.CONSULTI_SCHEMA)
public class HistoricoPostulacion {
    @Id
    @GeneratedValue()
    @Column(name = "ID", unique = true, updatable = false)
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ConsultiConstants.TIMEZONE_DATE)
    @Column(name = "fecha_creacion")
    private Date feCreacion;

    @Column(name = "resume_id")
    private String resumenId;

    @Column(name = "user_id")
    private String userId;

    public final static String ID_HISTORICO_POSTULACION = "id";
}
