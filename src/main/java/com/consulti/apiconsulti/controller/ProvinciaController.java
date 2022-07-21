package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.Provincia;
import com.consulti.apiconsulti.service.ProvinciaService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping({ ConsultiConstants.PROVINCIA_URL })
public class ProvinciaController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    ProvinciaService provinciaService;

    @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<Provincia> listar() throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROVINCIA_URL, ConsultiConstants.LISTA);
        GenericListResponse<Provincia> response = new GenericListResponse<>();
        response.setData(provinciaService.listar());
        return response;
    }

    @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<Provincia> listarPor(@RequestBody Provincia request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROVINCIA_URL, ConsultiConstants.LISTA_POR);
        GenericListResponse<Provincia> response = new GenericListResponse<>();
        response.setData(provinciaService.listarPor(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Provincia> guardar(@RequestBody Provincia request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROVINCIA_URL, ConsultiConstants.GUARDAR);
        GenericBasicResponse<Provincia> response = new GenericBasicResponse<>();
        response.setData(provinciaService.guardar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Provincia> actualizar(@RequestBody Provincia request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROVINCIA_URL, ConsultiConstants.ACTUALIZAR);
        GenericBasicResponse<Provincia> response = new GenericBasicResponse<>();
        response.setData(provinciaService.actualizar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Boolean> eliminar(@RequestBody Provincia request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROVINCIA_URL, ConsultiConstants.ELIMINAR);
        GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
        response.setData(provinciaService.eliminar(request));
        return response;
    }
}
