package com.neoris.microservicio.dominio.service;

import com.neoris.microservicio.dominio.dtos.CuentaDTO;
import com.neoris.microservicio.dominio.entity.Cuenta;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleValidationException;
import java.util.List;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Santiago
 */
public interface ICuentaService {

    public Cuenta findById(Long id) throws BussinesRuleException;

    public List<Cuenta> findAll();

    public Cuenta save(CuentaDTO cuentaDTO, BindingResult result) throws BussinesRuleValidationException,BussinesRuleException;

    public void put(CuentaDTO cuentaDTO, BindingResult result, Long id) throws BussinesRuleException, BussinesRuleValidationException;

   public void delete(Long id) throws BussinesRuleException;

}
