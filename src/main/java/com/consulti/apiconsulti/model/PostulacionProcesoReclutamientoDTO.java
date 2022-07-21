package com.consulti.apiconsulti.model;

import lombok.Data;

import java.util.UUID;
@Data
public class PostulacionProcesoReclutamientoDTO {
    private UUID userId;
    private UUID procesoReclutamientoId;
    private String usrCreacion;;
}
