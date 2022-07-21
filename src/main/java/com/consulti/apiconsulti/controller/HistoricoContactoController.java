package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.HistoricoContacto;
import com.consulti.apiconsulti.service.HistoricoContactoService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping({ ConsultiConstants.HISTORICO_CONTACTO_URL })
public class HistoricoContactoController {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    HistoricoContactoService historicoContactoService;

    @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<HistoricoContacto> listar() throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.HISTORICO_CONTACTO_URL, ConsultiConstants.LISTA);
        GenericListResponse<HistoricoContacto> response = new GenericListResponse<>();
        response.setData(historicoContactoService.listar());
        return response;
    }
    @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<HistoricoContacto> guardar(@RequestBody HistoricoContacto request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.HISTORICO_CONTACTO_URL, ConsultiConstants.GUARDAR);
        GenericBasicResponse<HistoricoContacto> response = new GenericBasicResponse<>();
        response.setData(historicoContactoService.guardar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<HistoricoContacto> listarPor(@RequestBody HistoricoContacto request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.HISTORICO_CONTACTO_URL, ConsultiConstants.LISTA_POR);
        GenericListResponse<HistoricoContacto> response = new GenericListResponse<>();
        response.setData(historicoContactoService.listarPor(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<HistoricoContacto> actualizar(@RequestBody HistoricoContacto request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.HISTORICO_CONTACTO_URL, ConsultiConstants.ACTUALIZAR);
        GenericBasicResponse<HistoricoContacto> response = new GenericBasicResponse<>();
        response.setData(historicoContactoService.actualizar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Boolean> eliminar(@RequestBody HistoricoContacto request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.HISTORICO_CONTACTO_URL, ConsultiConstants.ELIMINAR);
        GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
        response.setData(historicoContactoService.eliminar(request));
        return response;
    }
}
