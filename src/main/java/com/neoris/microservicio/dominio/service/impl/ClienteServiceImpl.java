package com.neoris.microservicio.dominio.service.impl;

import com.neoris.microservicio.dominio.dtos.ClienteDTO;
import com.neoris.microservicio.dominio.entity.Cliente;
import com.neoris.microservicio.dominio.repository.ClienteRepository;
import com.neoris.microservicio.dominio.service.IClienteService;
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

@Service
public class ClienteServiceImpl implements IClienteService {

    private static final Logger LOG = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final String INFO_URL = "api/cliente";

    ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true) //
    public Cliente findById(Long id) throws BussinesRuleException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isEmpty()) {
            return clienteRepository.findById(id).get();
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

    @Override
    @Transactional(readOnly = true) //
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) throws BussinesRuleException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isEmpty()) {
            clienteRepository.delete(cliente.get());
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }
    }

    @Override
    @Transactional() //
    public Cliente save(ClienteDTO clienteDTO, BindingResult result) throws BussinesRuleValidationException {

        if (result.hasErrors()) {
            BussinesRuleValidationException exception = new BussinesRuleValidationException(INFO_URL, result);
            throw exception;
        } else {
            Cliente c = new Cliente();
            c.setNombre(clienteDTO.getNombre());
            c.setGenero(clienteDTO.getGenero());
            c.setEdad(clienteDTO.getEdad());
            c.setIdentificacion(clienteDTO.getIdentificación());
            c.setDireccion(clienteDTO.getDireccion());
            c.setTelefono(clienteDTO.getTelefono());
            c.setContrasena(clienteDTO.getContrasena());
            c.setEstado(Boolean.TRUE);
            return clienteRepository.save(c);
        }

    }

    @Override
    public void put(ClienteDTO clienteDTO, BindingResult result, Long id) throws BussinesRuleException, BussinesRuleValidationException {
        Optional<Cliente> find = clienteRepository.findById(id);

        if (!find.isEmpty()) {
            if (result.hasErrors()) {
                BussinesRuleValidationException exception = new BussinesRuleValidationException(INFO_URL, result);
                throw exception;
            } else {
                find.get().setNombre(clienteDTO.getNombre());
                find.get().setGenero(clienteDTO.getGenero());
                find.get().setEdad(clienteDTO.getEdad());
                find.get().setIdentificacion(clienteDTO.getIdentificación());
                find.get().setDireccion(clienteDTO.getDireccion());
                find.get().setTelefono(clienteDTO.getTelefono());
                find.get().setContrasena(clienteDTO.getContrasena());
                find.get().setEstado(clienteDTO.getEstado());
                Cliente save = clienteRepository.save(find.get());
                clienteRepository.save(save);
            }
        } else {
            BussinesRuleException exception = new BussinesRuleException(INFO_URL);
            throw exception;
        }

    }

}
