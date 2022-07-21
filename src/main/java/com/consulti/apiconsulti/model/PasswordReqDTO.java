package com.consulti.apiconsulti.model;

import java.util.UUID;

import lombok.Data;

@Data
public class PasswordReqDTO {
  private String password;
  private String _password;
  private String token;
  private String username;
  private String usrModificacion;
  private UUID id;
}
