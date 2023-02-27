package com.neoris.microservicio.dominio.service.impl;

import com.neoris.microservicio.dominio.dtos.CuentaDTO;
import com.neoris.microservicio.dominio.entity.Cliente;
import com.neoris.microservicio.dominio.entity.Cuenta;
import com.neoris.microservicio.dominio.repository.CuentaRepository;
import com.neoris.microservicio.dominio.service.IClienteService;
import com.neoris.microservicio.dominio.service.ICuentaService;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleValidationException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Santiago Betancur
 */
@Service
public class CuentaServiceImpl implements ICuentaService {

    private static final Logger LOG = LoggerFactory.getLogger(CuentaServiceImpl.class);

    private final String INFO_URL = "api/cuenta";

    IClienteService iClienteService;
    CuentaRepository cuentaRepository;

    @Autowired
    public CuentaServiceImpl(IClienteService iClienteService, CuentaRepository cuentaRepository) {
        this.iClienteService = iClienteService;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    @Transactional(readOnly = true) //
    public Cuenta findById(Long id) throws BussinesRuleException {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        if (!cuenta.isEmpty()) {
            return cuentaRepository.findById(id).get();
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

    

    @Override
    @Transactional(readOnly = true) //
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    @Override
    @Transactional() //
    public Cuenta save(CuentaDTO cuentaDTO, BindingResult result) throws BussinesRuleValidationException,BussinesRuleException {
        
        if (result.hasErrors()) {
            BussinesRuleValidationException exception = new BussinesRuleValidationException(INFO_URL, result);
            throw exception;
        } else {
            Cliente cliente = iClienteService.findById(cuentaDTO.getIdCliente());
            Cuenta c = new Cuenta();
            c.setNumeroNuenta(cuentaDTO.getNumeroNuenta());
            c.setTipoCuenta(cuentaDTO.getTipoCuenta());
            c.setSaldoInicial(cuentaDTO.getSaldoInicial());
            c.setEstado(cuentaDTO.getEstado());
            c.setObjCliente(cliente);
            return cuentaRepository.save(c);
        }
    }

    @Override
    @Transactional() //
    public void put(CuentaDTO cuentaDTO, BindingResult result, Long id) throws BussinesRuleException, BussinesRuleValidationException {
        Optional<Cuenta> find = cuentaRepository.findById(id);

        if (!find.isEmpty()) {
            if (result.hasErrors()) {
                BussinesRuleValidationException exception = new BussinesRuleValidationException(INFO_URL, result);
                throw exception;
            } else {
                find.get().setEstado(cuentaDTO.getEstado());
                find.get().setNumeroNuenta(cuentaDTO.getNumeroNuenta());
                find.get().setSaldoInicial(cuentaDTO.getSaldoInicial());
                find.get().setTipoCuenta(cuentaDTO.getTipoCuenta());
                Cuenta save = cuentaRepository.save(find.get());
                cuentaRepository.save(save);
            }
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

    @Override
    @Transactional() //
    public void delete(Long id) throws BussinesRuleException {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        if (!cuenta.isEmpty()) {
            cuentaRepository.delete(cuenta.get());
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

}
