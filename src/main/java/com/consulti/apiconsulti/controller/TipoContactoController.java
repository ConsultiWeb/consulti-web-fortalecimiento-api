package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.InformacionContacto;
import com.consulti.apiconsulti.entity.TipoContacto;
import com.consulti.apiconsulti.service.InformacionContactoService;
import com.consulti.apiconsulti.service.TipoContactoService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping({ ConsultiConstants.TIPO_CONTACTO_URL })
public class TipoContactoController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    TipoContactoService tipoContactoService;
    @Autowired
    InformacionContactoService informacionContactoService;

    @PostMapping(path = { "/obtener_contactos" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<InformacionContacto> obtenerContactos(@RequestBody InformacionContacto request) throws Exception {
        GenericListResponse<InformacionContacto> response = new GenericListResponse<>();
        response.setData(informacionContactoService.listarPor(request));
        return response;
    }

    @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<TipoContacto> listar() throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.TIPO_CONTACTO_URL, ConsultiConstants.LISTA);
        GenericListResponse<TipoContacto> response = new GenericListResponse<>();
        response.setData(tipoContactoService.listar());
        return response;
    }

    @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<TipoContacto> listarPor(@RequestBody TipoContacto request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.TIPO_CONTACTO_URL, ConsultiConstants.LISTA_POR);
        GenericListResponse<TipoContacto> response = new GenericListResponse<>();
        response.setData(tipoContactoService.listarPor(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<TipoContacto> guardar(@RequestBody TipoContacto request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.TIPO_CONTACTO_URL, ConsultiConstants.GUARDAR);
        GenericBasicResponse<TipoContacto> response = new GenericBasicResponse<>();
        response.setData(tipoContactoService.guardar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<TipoContacto> actualizar(@RequestBody TipoContacto request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.TIPO_CONTACTO_URL, ConsultiConstants.ACTUALIZAR);
        GenericBasicResponse<TipoContacto> response = new GenericBasicResponse<>();
        response.setData(tipoContactoService.actualizar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Boolean> eliminar(@RequestBody TipoContacto request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.TIPO_CONTACTO_URL, ConsultiConstants.ELIMINAR);
        GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
        response.setData(tipoContactoService.eliminar(request));
        return response;
    }
}
