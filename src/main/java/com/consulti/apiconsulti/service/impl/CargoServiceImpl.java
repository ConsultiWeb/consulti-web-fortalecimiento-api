package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.Cargo;
import com.consulti.apiconsulti.repository.CargoRepository;
import com.consulti.apiconsulti.service.CargoService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.CargoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    CargoValidations cargoValidations;

    /* Funcion que lista todos los cargos
        @param: ninguno
        @return: List<Cargo>
    */
    @Override
    public List<Cargo> listar() throws GenericException {
        List<Cargo> response;
        try {
            GeneralUtils.validarMaximoDataLista(cargoRepository.count());
            response = cargoRepository.findAll();
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que lista todos los cargos por filtro
        @param: Cargo request
        @return: List<Cargo>
    */
    @Override
    public List<Cargo> listarPor(Cargo request) throws GenericException {
        List<Cargo> response;
        try {
            cargoValidations.validarCargoVacio(request);
            Example<Cargo> listaFiltros = Example.of(request);
            response = cargoRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, Cargo.ID_CARGO));
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que guarda un cargo
        @param: Cargo request
        @return: Cargo
    */
    public Cargo guardar(Cargo request) throws GenericException {
        Cargo response = new Cargo();
        try {
            request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
            request.setFeCreacion(new Date());
            cargoValidations.validarGuardar(request);
            response = cargoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que actualiza un cargo
        @param: Cargo request
        @return: Cargo
    */
    public Cargo actualizar(Cargo request) throws GenericException {
        Cargo response = new Cargo();
        try {
            request.setFeModificacion(new Date());
            request = cargoValidations.validarActualizar(request);
            response = cargoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
     /* Funcion que elimina un cargo
        @param: Cargo request
        @return: Cargo
    */
    public Boolean eliminar(Cargo request) throws GenericException {
        Boolean response = false;
        try {
            request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
            request.setFeModificacion(new Date());
            request = cargoValidations.validarActualizar(request);
            cargoRepository.saveAndFlush(request);
            response = true;
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
}
