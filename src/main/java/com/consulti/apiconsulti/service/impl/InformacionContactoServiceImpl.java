package com.consulti.apiconsulti.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.InformacionContacto;
import com.consulti.apiconsulti.entity.User;
import com.consulti.apiconsulti.repository.InformacionContactoRepository;
import com.consulti.apiconsulti.repository.UserRepository;
import com.consulti.apiconsulti.service.InformacionContactoService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.InformacionContactoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class InformacionContactoServiceImpl implements InformacionContactoService {

    @Autowired
    InformacionContactoRepository informacionContactoRepository;

    @Autowired
    InformacionContactoValidations informacionContactoValidations;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    /* Funcion que lista la informacion de contacto
        @param: ninguno
        @return: List<InformacionContacto>
    */
    @Override
    public List<InformacionContacto> listar() throws GenericException {
        List<InformacionContacto> response;
        try {
            GeneralUtils.validarMaximoDataLista(informacionContactoRepository.count());
            response = informacionContactoRepository.findAll();
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que lista la informacion de contacto por filtro
        @param: InformacionContacto request
        @return: List<InformacionContacto>
    */
    @Override
    public List<InformacionContacto> listarPor(InformacionContacto request) throws GenericException {
        List<InformacionContacto> response;
        try {
            informacionContactoValidations.validarInformacionContactoVacio(request);
            Example<InformacionContacto> listaFiltros = Example.of(request);
            response = informacionContactoRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, InformacionContacto.ID_INFORMACION_CONTACTO));
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
     /* Funcion que guarda un registro de informacion de contacto
        @param: InformacionContacto request
        @return: InformacionContacto
    */
    public InformacionContacto guardar(InformacionContacto request) throws GenericException {
        InformacionContacto response = new InformacionContacto();
        try {
            request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
            request.setFeCreacion(new Date());
            informacionContactoValidations.validarGuardar(request);
            response = informacionContactoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que actualiza un registro de informacion de contacto
        @param: InformacionContacto request
        @return: InformacionContacto
    */
    public InformacionContacto actualizar(InformacionContacto request) throws GenericException {
        InformacionContacto response = new InformacionContacto();
        try {
            request.setFeModificacion(new Date());
            Optional<User> optionalUser  = userRepository.findById(request.getUserId());
            User resu = optionalUser.orElse(null);
            request.setUsrModificacion(resu.getUsername());
            request = informacionContactoValidations.validarActualizar(request);
            response = informacionContactoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
     /* Funcion que elimina un registro de informacion de contacto
        @param: InformacionContacto request
        @return: InformacionContacto
    */
    public Boolean eliminar(UUID user) throws GenericException {
        Boolean response = false;

            List<InformacionContacto> items =
                    entityManager.createQuery("from InformacionContacto where user_id=:user", InformacionContacto.class)
                            .setParameter("user", user)
                            .getResultList();
            for(InformacionContacto entity : items){
                entity.setEstado("Eliminado");
                entity.setFeModificacion(new Date());
                Optional<User> optionalUser  = userRepository.findById(user);
                User resu = optionalUser.orElse(null);
                entity.setUsrModificacion(resu.getUsername());
                informacionContactoRepository.saveAndFlush(entity);
            }
            response = true;

        return response;
    }
}
