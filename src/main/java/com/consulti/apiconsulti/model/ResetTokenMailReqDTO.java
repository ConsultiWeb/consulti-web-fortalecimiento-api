package com.consulti.apiconsulti.model;

import java.util.Locale;

import com.consulti.apiconsulti.entity.User;

import lombok.Data;

@Data
public class ResetTokenMailReqDTO {
  String contextPath;
  Locale locale;
  String token;
  User user;
}
