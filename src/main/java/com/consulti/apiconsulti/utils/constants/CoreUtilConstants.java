package com.consulti.apiconsulti.utils.constants;

public abstract class CoreUtilConstants {
	private CoreUtilConstants() {
	}

	public static final String GROUP_LOG = "log";
	public static final String TOPIC_LOG_ASYN = "logAsynchro";
	public static final String OP_ERROR_LOG = "errorLog";
	public static final Integer MISSING_VALUES;
	public static final Integer INFORMATIVE_VALUES;
	public static final Integer EXISTING_VALUES;
	public static final Integer ERROR_PARSE_VALUES;
	public static final String STATUS_ERROR = "ERROR";
	public static final String MSG_ERROR_REQ_BODY = "Se requiere el request body";
	public static final String MSG_ERROR_FORM_BODY = "Error en el formato del request body";
	public static final String MSG_ERROR_NULL_POINTER = "Se produjo un error NullPointerException";
	public static final String MSG_ERROR_REST_SSL = "Activar la propiedad ms.rest.ssl.enable=S para consumos con SSL";

	static {
		MISSING_VALUES = 10;
		INFORMATIVE_VALUES = 11;
		EXISTING_VALUES = 12;
		ERROR_PARSE_VALUES = 13;
	}
}
