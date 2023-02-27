/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.dominio.service;

import com.neoris.microservicio.dominio.dtos.MovimientoDTO;
import com.neoris.microservicio.dominio.dtos.MovimientoResponse;
import com.neoris.microservicio.dominio.entity.Movimiento;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleMovimientoValidationException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleValidationException;
import java.util.List;
import org.springframework.validation.BindingResult;

/**
 *
 * @author rizzoli
 */
public interface IMovimientoService {

    public Movimiento findById(Long id) throws BussinesRuleException;

    public List<Movimiento> findAll();

    public MovimientoResponse save(MovimientoDTO movimientoDTO, BindingResult result) throws BussinesRuleValidationException, BussinesRuleException,BussinesRuleMovimientoValidationException;

    public void put(MovimientoDTO movimientoDTO, BindingResult result, Long id) throws BussinesRuleException, BussinesRuleValidationException;

    public void delete(Long id) throws BussinesRuleException;
}
