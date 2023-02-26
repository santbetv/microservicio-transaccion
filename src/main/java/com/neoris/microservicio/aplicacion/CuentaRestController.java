package com.neoris.microservicio.aplicacion;

import com.neoris.microservicio.dominio.dtos.CuentaDTO;
import com.neoris.microservicio.dominio.entity.Cuenta;
import com.neoris.microservicio.dominio.service.ICuentaService;
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

/**
 *
 * @author Santiago Betancur
 */
@RestController
@RequestMapping("/api")
public class CuentaRestController {

    private static final Logger LOG = LoggerFactory.getLogger(CuentaRestController.class);

    ICuentaService iCuentaService;

    @Autowired
    public CuentaRestController(ICuentaService iCuentaService) {
        this.iCuentaService = iCuentaService;
    }

    @GetMapping("/cuentas")
    public List<Cuenta> list() {
        return iCuentaService.findAll();
    }

    @GetMapping("/cuentas/{id}")
    public Cuenta get(@PathVariable Long id) throws BussinesRuleException {
        return iCuentaService.findById(id);
    }

    @PostMapping("/cuentas")
    public ResponseEntity<?> post(@Valid @RequestBody CuentaDTO input, BindingResult result) throws BussinesRuleValidationException, BussinesRuleException {
        Cuenta save = iCuentaService.save(input, result);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody CuentaDTO input, BindingResult result, @PathVariable Long id) throws BussinesRuleException, BussinesRuleValidationException {
        iCuentaService.put(input, result, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws BussinesRuleException {
        iCuentaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);

    }

}
