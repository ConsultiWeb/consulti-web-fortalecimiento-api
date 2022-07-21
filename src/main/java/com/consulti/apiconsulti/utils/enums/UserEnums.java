package com.consulti.apiconsulti.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserEnums {
  @AllArgsConstructor
  public enum Endpoint {
    listUserBy("listUserBy");

    @Getter
    private String value;
  }
}
