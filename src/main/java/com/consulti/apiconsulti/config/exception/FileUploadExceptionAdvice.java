package com.consulti.apiconsulti.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.consulti.apiconsulti.model.FileResDTO;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<FileResDTO> handleMaxSizeException(MaxUploadSizeExceededException exc) {
    FileResDTO res = new FileResDTO();
    res.setName("test");
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(res);
  }
}
