package com.consulti.apiconsulti.model;

import lombok.Data;

@Data
public class LogErrorDTO {
	private String paquete;
	private String clase;
	private String metodo;
	private Integer linea;
	private String aplicacion;
}
