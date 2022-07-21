package com.consulti.apiconsulti.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class GenericBasicResponse<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  private int code;
  private String status;
  private String message;
  private T data;

  public GenericBasicResponse() {
    this.code = 0;
    this.status = "OK";
    this.message = "Transacción realizada correctamente";
  }
}
