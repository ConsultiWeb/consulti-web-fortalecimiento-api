package com.consulti.apiconsulti.controller;

import com.consulti.apiconsulti.config.response.GenericBasicResponse;
import com.consulti.apiconsulti.config.response.GenericListResponse;
import com.consulti.apiconsulti.entity.Cargo;
import com.consulti.apiconsulti.service.CargoService;
import com.consulti.apiconsulti.utils.constants.ConsultiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping({ ConsultiConstants.CARGO_URL })
public class CargoController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    CargoService cargoService;

    @GetMapping(path = { ConsultiConstants.LISTA }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<Cargo> listar() throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CARGO_URL, ConsultiConstants.LISTA);
        GenericListResponse<Cargo> response = new GenericListResponse<>();
        response.setData(cargoService.listar());
        return response;
    }

    @PostMapping(path = { ConsultiConstants.LISTA_POR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericListResponse<Cargo> listarPor(@RequestBody Cargo request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CARGO_URL, ConsultiConstants.LISTA_POR);
        GenericListResponse<Cargo> response = new GenericListResponse<>();
        response.setData(cargoService.listarPor(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.GUARDAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Cargo> guardar(@RequestBody Cargo request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CARGO_URL, ConsultiConstants.GUARDAR);
        GenericBasicResponse<Cargo> response = new GenericBasicResponse<>();
        response.setData(cargoService.guardar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ACTUALIZAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Cargo> actualizar(@RequestBody Cargo request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CARGO_URL, ConsultiConstants.ACTUALIZAR);
        GenericBasicResponse<Cargo> response = new GenericBasicResponse<>();
        response.setData(cargoService.actualizar(request));
        return response;
    }

    @PostMapping(path = { ConsultiConstants.ELIMINAR }, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<Boolean> eliminar(@RequestBody Cargo request) throws Exception {
        log.info(ConsultiConstants.MENSAJE_PETICION, ConsultiConstants.CARGO_URL, ConsultiConstants.ELIMINAR);
        GenericBasicResponse<Boolean> response = new GenericBasicResponse<>();
        response.setData(cargoService.eliminar(request));
        return response;
    }
}
