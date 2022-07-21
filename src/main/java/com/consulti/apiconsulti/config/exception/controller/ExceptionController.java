package com.consulti.apiconsulti.config.exception.controller;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
@RestController
public class ExceptionController {
	Logger log;
	private String mensajeError;
	private String paqueteError;
	private String claseError;
	private String metodoError;
	private Integer lineaError;

	public ExceptionController() {
		this.log = LogManager.getLogger(this.getClass());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<GenericBasicResponse<String>> handleException(final Exception e) throws Exception {
		final GenericBasicResponse<String> response = new GenericBasicResponse<String>();
		Boolean errorGenerico = false;
		if (e instanceof GenericException) {
			errorGenerico = true;
			final GenericException ex = (GenericException) e;
			this.mensajeError = ex.getMessageError();
			response.setCode(ex.getCodeError());
			response.setStatus(ex.getStatusError());
			response.setMessage(this.mensajeError);
		} else if (e instanceof NullPointerException) {
			this.mensajeError = "Se produjo un error NullPointerException";
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof HttpRequestMethodNotSupportedException) {
			final HttpRequestMethodNotSupportedException ex2 = (HttpRequestMethodNotSupportedException) e;
			this.mensajeError = ex2.getMessage();
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof HttpMediaTypeNotSupportedException) {
			final HttpMediaTypeNotSupportedException ex3 = (HttpMediaTypeNotSupportedException) e;
			this.mensajeError = ex3.getMessage();
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof HttpMessageNotReadableException) {
			final HttpMessageNotReadableException ex4 = (HttpMessageNotReadableException) e;
			if (ex4.contains((Class) InvalidFormatException.class)) {
				this.mensajeError = ex4.getLocalizedMessage();
			} else if (ex4.contains((Class) MismatchedInputException.class)) {
				this.mensajeError = ex4.getLocalizedMessage();
			} else {
				this.mensajeError = "Se requiere el request body";
			}
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof PropertyReferenceException) {
			final PropertyReferenceException ex5 = (PropertyReferenceException) e;
			this.mensajeError = ex5.getMessage();
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof DataIntegrityViolationException) {
			final DataIntegrityViolationException ex6 = (DataIntegrityViolationException) e;
			this.mensajeError = ex6.getRootCause().getMessage();
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof DataAccessException) {
			final DataAccessException ex7 = (DataAccessException) e;
			this.mensajeError = ex7.getRootCause().getMessage();
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof CannotCreateTransactionException) {
			final CannotCreateTransactionException ex8 = (CannotCreateTransactionException) e;
			this.mensajeError = ex8.getRootCause().getMessage();
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof ServletRequestBindingException) {
			final ServletRequestBindingException ex9 = (ServletRequestBindingException) e;
			this.mensajeError = ex9.getRootCause().getMessage();
			response.setCode(100);
			response.setMessage("Se requiere el request body");
			response.setStatus("ERROR");
		} else if (e instanceof ResourceAccessException) {
			final ResourceAccessException ex10 = (ResourceAccessException) e;
			this.mensajeError = ex10.getMessage();
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else if (e instanceof HttpClientErrorException) {
			final HttpClientErrorException ex11 = (HttpClientErrorException) e;
			this.mensajeError = ex11.getMessage();
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		} else {
			if (e instanceof Exception) {
				this.mensajeError = ((e.getMessage() != null) ? e.getMessage()
						: "Ha ocurrido un problema. Por favor informar a Sistemas!");
			} else {
				e.printStackTrace();
				this.mensajeError = "Ha ocurrido un problema. Por favor informar a Sistemas!";
			}
			response.setCode(100);
			response.setMessage(this.mensajeError);
			response.setStatus("ERROR");
		}
		this.paqueteError = e.getStackTrace()[0].getClassName();
		this.claseError = e.getStackTrace()[0].getFileName();
		this.metodoError = e.getStackTrace()[0].getMethodName();
		this.lineaError = e.getStackTrace()[0].getLineNumber();
		this.log.error("[{}][{}][{}][{}] - {}", (Object) this.paqueteError, (Object) this.claseError,
				(Object) this.metodoError, (Object) this.lineaError, (Object) this.mensajeError);
		return (ResponseEntity<GenericBasicResponse<String>>) new ResponseEntity((Object) response,
				((boolean) errorGenerico) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}
}
