package com.consulti.apiconsulti.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {
  private String fileName;
  private String fileUrl;
  private String fileType;
  private Long size;
}
