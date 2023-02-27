/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.dominio.service.impl;

import com.neoris.microservicio.dominio.dtos.MovimientoDTO;
import com.neoris.microservicio.dominio.dtos.MovimientoResponse;
import com.neoris.microservicio.dominio.entity.Cliente;
import com.neoris.microservicio.dominio.entity.Cuenta;
import com.neoris.microservicio.dominio.entity.Movimiento;
import com.neoris.microservicio.dominio.entity.TipoMovimiento;
import com.neoris.microservicio.dominio.repository.MovimientoRepository;
import com.neoris.microservicio.dominio.service.IClienteService;
import com.neoris.microservicio.dominio.service.ICuentaService;
import com.neoris.microservicio.dominio.service.IMovimientoService;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleMovimientoValidationException;
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
public class MovimientoServiceImpl implements IMovimientoService {

    private static final Logger LOG = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    private final String INFO_URL = "api/movimiento";
    private static final String SALDO_NO_DISPONIBLE = "Saldo no disponible";

    IClienteService iClienteService;
    ICuentaService iCuentaService;
    MovimientoRepository movimientoRepository;

    @Autowired
    public MovimientoServiceImpl(IClienteService iClienteService, ICuentaService iCuentaService, MovimientoRepository movimientoRepository) {
        this.iClienteService = iClienteService;
        this.iCuentaService = iCuentaService;
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    @Transactional(readOnly = true) //
    public Movimiento findById(Long id) throws BussinesRuleException {
        Optional<Movimiento> cuenta = movimientoRepository.findById(id);
        if (!cuenta.isEmpty()) {
            return movimientoRepository.findById(id).get();
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

    @Override
    @Transactional(readOnly = true) //
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }

    @Override
    @Transactional() //
    public MovimientoResponse save(MovimientoDTO movimientoDTO, BindingResult result) throws BussinesRuleValidationException, BussinesRuleException, BussinesRuleMovimientoValidationException {
        
        if (result.hasErrors()) {
            BussinesRuleValidationException exception = new BussinesRuleValidationException(INFO_URL, result);
            throw exception;
        } else {
            Cuenta cuenta = iCuentaService.findById(movimientoDTO.getIdCuenta());
            Cliente cliente = iClienteService.findById(movimientoDTO.getIdCliente());
            Double infoUl = movimientoRepository.ultimoSaldo(movimientoDTO.getIdCliente(), movimientoDTO.getIdCuenta());

            Movimiento m = new Movimiento();
            MovimientoResponse mr = new MovimientoResponse();
            if (TipoMovimiento.RETIRO.getValor().equals(movimientoDTO.getTipoMovimiento()) && cuenta.getSaldoInicial() == 0) {
                BussinesRuleMovimientoValidationException exception = new BussinesRuleMovimientoValidationException(INFO_URL, SALDO_NO_DISPONIBLE);
                throw exception;
            } else {
                if (TipoMovimiento.RETIRO.getValor().equals(movimientoDTO.getTipoMovimiento()) && infoUl != null) {
                    BussinesRuleMovimientoValidationException exception = new BussinesRuleMovimientoValidationException(INFO_URL, SALDO_NO_DISPONIBLE);
                    throw exception;
                }
                m.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
                m.setValor(movimientoDTO.getValor());
                m.setSaldo(m.agregarSaldoTotal(cuenta.getSaldoInicial(), movimientoDTO.getTipoMovimiento(), infoUl));
                m.setObjCuentaMovimiento(cuenta);
                m.setObjClienteMovimiento(cliente);
                movimientoRepository.save(m);
            }
            return mr.build(m);
        }
    }

    @Override
    @Transactional() //
    public void put(MovimientoDTO movimientoDTO, BindingResult result, Long id) throws BussinesRuleException, BussinesRuleValidationException {
        Optional<Movimiento> find = movimientoRepository.findById(id);
        if (!find.isEmpty()) {
            if (result.hasErrors()) {
                BussinesRuleValidationException exception = new BussinesRuleValidationException(INFO_URL, result);
                throw exception;
            } else {
                find.get().setValor(movimientoDTO.getValor());
                find.get().setTipoMovimiento(movimientoDTO.getTipoMovimiento());
                find.get().setSaldo(find.get().actualizarSaldoTotal());
                Movimiento save = movimientoRepository.save(find.get());
            }
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

    @Override
    public void delete(Long id) throws BussinesRuleException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
