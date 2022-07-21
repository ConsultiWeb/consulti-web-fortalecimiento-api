package com.consulti.apiconsulti.config.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GenericException extends Exception {
	private static final long serialVersionUID = 1L;
	private Integer codeError;
	private String statusError;
	private String messageError;

	public GenericException() {
		this.statusError = "ERROR";
	}

	public GenericException(final String message) {
		super(message);
		this.statusError = "ERROR";
		this.setCodeError(99);
		this.setMessageError(message);
	}

	public GenericException(final Throwable throwable) {
		super(throwable);
		this.statusError = "ERROR";
	}

	public GenericException(final String message, final Integer errorCode) {
		super(message);
		this.statusError = "ERROR";
		this.setCodeError(errorCode);
		this.setMessageError(message);
	}

	public GenericException(final String message, final Throwable throwable) {
		super(message, throwable);
		this.statusError = "ERROR";
	}
}
