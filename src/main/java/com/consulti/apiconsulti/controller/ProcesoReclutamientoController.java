package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.ProcesoReclutamiento;
import com.consulti.apiconsulti.service.ProcesoReclutamientoService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping({ ConsultiConstants.PROCESO_RECLUTAMIENTO_URL })
public class ProcesoReclutamientoController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    ProcesoReclutamientoService procesoReclutamientoService;

    @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<ProcesoReclutamiento> listar() throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROCESO_RECLUTAMIENTO_URL, ConsultiConstants.LISTA);
        GenericListResponse<ProcesoReclutamiento> response = new GenericListResponse<>();
        response.setData(procesoReclutamientoService.listar());
        return response;
    }

    @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<ProcesoReclutamiento> listarPor(@RequestBody ProcesoReclutamiento request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROCESO_RECLUTAMIENTO_URL, ConsultiConstants.LISTA_POR);
        GenericListResponse<ProcesoReclutamiento> response = new GenericListResponse<>();
        response.setData(procesoReclutamientoService.listarPor(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<ProcesoReclutamiento> guardar(@RequestBody ProcesoReclutamiento request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROCESO_RECLUTAMIENTO_URL, ConsultiConstants.GUARDAR);
        GenericBasicResponse<ProcesoReclutamiento> response = new GenericBasicResponse<>();
        response.setData(procesoReclutamientoService.guardar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<ProcesoReclutamiento> actualizar(@RequestBody ProcesoReclutamiento request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROCESO_RECLUTAMIENTO_URL, ConsultiConstants.ACTUALIZAR);
        GenericBasicResponse<ProcesoReclutamiento> response = new GenericBasicResponse<>();
        response.setData(procesoReclutamientoService.actualizar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Boolean> eliminar(@RequestBody ProcesoReclutamiento request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.PROCESO_RECLUTAMIENTO_URL, ConsultiConstants.ELIMINAR);
        GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
        response.setData(procesoReclutamientoService.eliminar(request));
        return response;
    }
}
