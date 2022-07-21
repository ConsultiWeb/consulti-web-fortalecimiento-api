package com.consulti.apiconsulti.utils;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import com.consulti.apiconsulti.utils.constants.CoreUtilConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class GeneralUtils {
  static Logger log;

  public static void validarMaximoDataLista(long count) throws GenericException {
    if (count > ConsultiConstants.MAXIMO_DATA_LIST) {
      throw new GenericException("El numero de datos que responde la lista supera los "
          + ConsultiConstants.MAXIMO_DATA_LIST + " datos permitidos, por favor utilizar el proceso de filtrado",
          CoreUtilConstants.ERROR_PARSE_VALUES);
    }
  }

  public static Boolean validarEmail(final String email) {
    final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    final Pattern pattern = Pattern.compile(emailPattern);
    final Matcher mather = pattern.matcher(email);
    if (mather.matches()) {
      return true;
    }
    return false;
  }

  public static <T> T mapearObjDeserializado(final Object objDeserializado, final Class<T> tipoValueClass) {
    final ObjectMapper objMapper = new ObjectMapper();
    return (T) objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .convertValue(objDeserializado, (Class) tipoValueClass);
  }

  public static <T> List<T> mapearListObjDeserializado(final Object objDeserializado, final Class<T> tipoValueClass) {
    List<T> lista = new ArrayList<T>();
    final ObjectMapper objMapper = new ObjectMapper();
    objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try {
      lista = (List<T>) objMapper.readValue(objMapper.writeValueAsString(objDeserializado),
          (JavaType) objMapper.getTypeFactory().constructCollectionType((Class) List.class, (Class) tipoValueClass));
    } catch (IOException e) {
      GeneralUtils.log.error("Error en formato mapearListObjDeserializado " + e.getLocalizedMessage());
    }
    return lista;
  }

  public static Date agregarDiasAFecha(Date fecha, Integer numeroDias) {
    Instant instant = fecha.toInstant();
    ZoneId zoneId = ZoneId.of(ConsultiConstants.TIMEZONE_DATE);
    ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
    ZonedDateTime zdtDiasdespues = zdt.plus(numeroDias, ChronoUnit.DAYS);
    return Date.from(zdtDiasdespues.toInstant());
  }
  public static boolean validarCedula(String document) {
    byte sum = 0;
    try {
      if (document.trim().length() != 10)
        return false;
      String[] data = document.split("");
      byte verifier = Byte.parseByte(data[0] + data[1]);
      if (verifier < 1 || verifier > 24)
        return false;
      byte[] digits = new byte[data.length];
      for (byte i = 0; i < digits.length; i++)
        digits[i] = Byte.parseByte(data[i]);
      if (digits[2] > 6)
        return false;
      for (byte i = 0; i < digits.length - 1; i++) {
        if (i % 2 == 0) {
          verifier = (byte) (digits[i] * 2);
          if (verifier > 9)
            verifier = (byte) (verifier - 9);
        } else
          verifier = (byte) (digits[i] * 1);
        sum = (byte) (sum + verifier);
      }
      if ((sum - (sum % 10) + 10 - sum) == digits[9])
        return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;

  }
}
