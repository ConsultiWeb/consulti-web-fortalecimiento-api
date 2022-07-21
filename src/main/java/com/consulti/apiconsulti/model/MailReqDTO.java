package com.consulti.apiconsulti.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailReqDTO {
  private String para;
  private String de;
  private List<String> cc;
  private List<String> bcc;
  private String asunto;
  private String texto;
  private String rutaArchivo;
  private List<String> rutasListaArchivos;
  private Map<String, Object> plantilla;
}
