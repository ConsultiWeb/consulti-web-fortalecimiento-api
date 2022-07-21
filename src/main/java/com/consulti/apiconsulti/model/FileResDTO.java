package com.consulti.apiconsulti.model;

import lombok.Data;

@Data
public class FileResDTO {
  private String name;
  private String url;
  private String type;
  private long size;
}
