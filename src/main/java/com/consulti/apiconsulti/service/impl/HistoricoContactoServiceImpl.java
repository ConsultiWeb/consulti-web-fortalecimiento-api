package com.consulti.apiconsulti.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.consulti.apiconsulti.config.exception.GenericException;
import com.consulti.apiconsulti.entity.HistoricoContacto;
import com.consulti.apiconsulti.repository.HistoricoContactoRepository;
import com.consulti.apiconsulti.repository.UserRepository;
import com.consulti.apiconsulti.service.HistoricoContactoService;
import com.consulti.apiconsulti.utils.GeneralUtils;
import com.consulti.apiconsulti.utils.enums.StatusHandlerEnum;
import com.consulti.apiconsulti.utils.validations.HistoricoContactoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HistoricoContactoServiceImpl implements HistoricoContactoService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    HistoricoContactoRepository historicoContactoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoricoContactoValidations historicoContactoValidations;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${mail.de}")
    String noReplyMail;

     /* Funcion que obtiene la lista del historico de contactos importados
        @param: ninguno
        @return: List<HistoricoContacto>
    */
    @Override
    public List<HistoricoContacto> listar() throws GenericException {
        List<HistoricoContacto> response;
        try {
            GeneralUtils.validarMaximoDataLista(historicoContactoRepository.count());
            response = historicoContactoRepository.findAll();
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que obtiene la lista del historico de contactos importados por filtro
        @param: HistoricoContacto request
        @return: List<HistoricoContacto>
    */
    @Override
    public List<HistoricoContacto> listarPor(HistoricoContacto request) throws GenericException {
        List<HistoricoContacto> response = new ArrayList<>();
        try {
            historicoContactoValidations.validarHistoricoContactoVacio(request);
            Example<HistoricoContacto> listaFiltros = Example.of(request);
            List <HistoricoContacto> resp = historicoContactoRepository.findAll(listaFiltros, Sort.by(Sort.Direction.ASC, HistoricoContacto.ID_HISTORICO_CONTACTO));
            for (HistoricoContacto element : resp) {
                String interes = userRepository.existsByEmail(element.getEmail())?"Interesado":"Contacto";
                element.setEstaInteresado(interes);
                response.add(element);
            }
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que guarda un registro del historiuco
        @param: HistoricoContacto request
        @return: HistoricoContacto
    */
    public HistoricoContacto guardar(HistoricoContacto request) throws GenericException {
        HistoricoContacto response = new HistoricoContacto();
        try {
            request.setEstado(StatusHandlerEnum.ACTIVO.getValue());
            request.setFeCreacion(new Date());
            request.setEstaInteresado("Contacto");
            request.setUsrCreacion(request.getUsrCreacion() == null ? request.getUsrCreacion() : request.getUsrCreacion());
            historicoContactoValidations.validarGuardar(request);
            response = historicoContactoRepository.saveAndFlush(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que actualiza un registro del historiuco
        @param: HistoricoContacto request
        @return: HistoricoContacto
    */
    public HistoricoContacto actualizar(HistoricoContacto request) throws GenericException {
        HistoricoContacto response = new HistoricoContacto();
        try {
            request.setFeModificacion(new Date());
            request = historicoContactoValidations.validarActualizar(request);
            response = historicoContactoRepository.save(request);
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
    /* Funcion que elimina un registro del historiuco
        @param: HistoricoContacto request
        @return: HistoricoContacto
    */
    public Boolean eliminar(HistoricoContacto request) throws GenericException {
        Boolean response = false;
        try {
            request.setEstado(StatusHandlerEnum.ELIMINADO.getValue());
            request.setFeModificacion(new Date());
            request = historicoContactoValidations.validarActualizar(request);
            historicoContactoRepository.saveAndFlush(request);
            response = true;
        } catch (GenericException e) {
            throw new GenericException(e.getMessageError(), e.getCodeError());
        }
        return response;
    }
}
