package com.consulti.apiconsulti.model;

import java.util.UUID;

import lombok.Data;

@Data
public class MailPostulacionReqDTO extends MailReqDTO {
  private UUID postulacionId;
  private UUID userId;
  private String username;
}
