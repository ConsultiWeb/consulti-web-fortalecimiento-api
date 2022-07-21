package com.consulti.apiconsulti.config.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class GenericListResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String status;
    private String message;
    private List<T> data;

    public GenericListResponse() {
        this.code = 0;
        this.status = "OK";
        this.message = "Transacci\u00f3n realizada correctamente";
    }

}
