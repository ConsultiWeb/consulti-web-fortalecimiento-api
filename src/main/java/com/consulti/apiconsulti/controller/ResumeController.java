package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.Resume;
import com.consulti.apiconsulti.model.DetalleResumenDTO;
import com.consulti.apiconsulti.service.ResumeService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping({ ConsultiConstants.RESUME_URL })
public class ResumeController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    ResumeService resumeService;

    @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<Resume> listar() throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.RESUME_URL, ConsultiConstants.LISTA);
        GenericListResponse<Resume> response = new GenericListResponse<>();
        response.setData(resumeService.listar());
        return response;
    }

    @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<Resume> listarPor(@RequestBody Resume request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.RESUME_URL, ConsultiConstants.LISTA_POR);
        GenericListResponse<Resume> response = new GenericListResponse<>();
        response.setData(resumeService.listarPor(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Resume> guardar(@RequestBody DetalleResumenDTO request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.RESUME_URL, ConsultiConstants.GUARDAR);
        GenericBasicResponse<Resume> response = new GenericBasicResponse<>();
        response.setData(resumeService.guardarDetalleResumen(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Resume> actualizar(@RequestBody Resume request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.RESUME_URL, ConsultiConstants.ACTUALIZAR);
        GenericBasicResponse<Resume> response = new GenericBasicResponse<>();
        response.setData(resumeService.actualizar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Boolean> eliminar(@RequestBody Resume request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.RESUME_URL, ConsultiConstants.ELIMINAR);
        GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
        response.setData(resumeService.eliminar(request));
        return response;
    }
}
