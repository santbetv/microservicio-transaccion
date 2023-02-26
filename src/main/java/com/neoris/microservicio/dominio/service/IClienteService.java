package com.neoris.microservicio.dominio.service;

import com.neoris.microservicio.dominio.dtos.ClienteDTO;
import com.neoris.microservicio.dominio.entity.Cliente;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleValidationException;
import java.util.List;

import org.springframework.validation.BindingResult;

public interface IClienteService {

    public Cliente findById(Long id) throws BussinesRuleException;

    public List<Cliente> findAll();

    public Cliente save(ClienteDTO clienteDTO, BindingResult result) throws BussinesRuleValidationException;

    public void put(ClienteDTO clienteDTO, BindingResult result, Long id) throws BussinesRuleException, BussinesRuleValidationException;

    public void delete(Long id) throws BussinesRuleException;

}
