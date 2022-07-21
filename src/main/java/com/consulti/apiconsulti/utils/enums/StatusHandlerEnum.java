package com.consulti.apiconsulti.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusHandlerEnum {
  ACTIVO("Activo"), INACTIVO("Inactivo"), MODIFICADO("Modificado"), ELIMINADO("Eliminado"), PENDIENTE("Pendiente"),
  FINALIZADO("Finalizado"), INVITADO("Invitado");

  @Getter
  private String value;
}
