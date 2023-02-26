package com.neoris.microservicio.aplicacion;

import com.neoris.microservicio.dominio.dtos.ClienteDTO;
import com.neoris.microservicio.dominio.entity.Cliente;
import com.neoris.microservicio.dominio.entity.Persona;
import com.neoris.microservicio.dominio.service.IClienteService;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleValidationException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

    private static final Logger LOG = LoggerFactory.getLogger(ClienteRestController.class);

    IClienteService iClienteService;

    @Autowired
    public ClienteRestController(IClienteService iClienteService) {
        this.iClienteService = iClienteService;
    }

    @GetMapping("/clientes/{id}")
    public Cliente get(@PathVariable Long id) throws BussinesRuleException {
        return iClienteService.findById(id);
    }

    @GetMapping("/clientes")
    public List<Cliente> findAll() {
        return iClienteService.findAll();
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> post(@Valid @RequestBody ClienteDTO input, BindingResult result) throws BussinesRuleValidationException {
        Persona save = iClienteService.save(input, result);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws BussinesRuleException {
        iClienteService.delete(id);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody ClienteDTO input, BindingResult result, @PathVariable Long id) throws BussinesRuleException, BussinesRuleValidationException {
        iClienteService.put(input, result, id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
