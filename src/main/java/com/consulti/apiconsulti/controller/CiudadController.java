package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.Ciudad;
import com.consulti.apiconsulti.service.CiudadService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping({ ConsultiConstants.CIUDAD_URL })
public class CiudadController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    CiudadService ciudadService;

    @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<Ciudad> listar() throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CIUDAD_URL, ConsultiConstants.LISTA);
        GenericListResponse<Ciudad> response = new GenericListResponse<>();
        response.setData(ciudadService.listar());
        return response;
    }

    @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<Ciudad> listarPor(@RequestBody Ciudad request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CIUDAD_URL, ConsultiConstants.LISTA_POR);
        GenericListResponse<Ciudad> response = new GenericListResponse<>();
        response.setData(ciudadService.listarPor(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Ciudad> guardar(@RequestBody Ciudad request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CIUDAD_URL, ConsultiConstants.GUARDAR);
        GenericBasicResponse<Ciudad> response = new GenericBasicResponse<>();
        response.setData(ciudadService.guardar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Ciudad> actualizar(@RequestBody Ciudad request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CIUDAD_URL, ConsultiConstants.ACTUALIZAR);
        GenericBasicResponse<Ciudad> response = new GenericBasicResponse<>();
        response.setData(ciudadService.actualizar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Boolean> eliminar(@RequestBody Ciudad request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CIUDAD_URL, ConsultiConstants.ELIMINAR);
        GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
        response.setData(ciudadService.eliminar(request));
        return response;
    }
}
