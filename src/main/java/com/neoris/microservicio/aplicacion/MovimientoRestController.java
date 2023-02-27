/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.aplicacion;

import com.neoris.microservicio.dominio.dtos.MovimientoDTO;
import com.neoris.microservicio.dominio.dtos.MovimientoResponse;
import com.neoris.microservicio.dominio.entity.Movimiento;
import com.neoris.microservicio.dominio.service.IMovimientoService;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleMovimientoValidationException;
import com.neoris.microservicio.infraestructura.exception.BussinesRuleValidationException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Santiago Betancur
 */
@RestController
@RequestMapping("/api")
public class MovimientoRestController {
    
    private static final Logger LOG = LoggerFactory.getLogger(MovimientoRestController.class);

    IMovimientoService iMovimientoService;

    @Autowired
    public MovimientoRestController(IMovimientoService iMovimientoService) {
        this.iMovimientoService = iMovimientoService;
    }
    
    @GetMapping("/movimientos")
    public List<Movimiento> list() {
        return iMovimientoService.findAll();
    }
    
    @GetMapping("/movimientos/{id}")
    public Movimiento get(@PathVariable Long id) throws BussinesRuleException {
        return iMovimientoService.findById(id);
    }
    
    @PostMapping("/movimientos")
    public ResponseEntity<?> post(@Valid @RequestBody MovimientoDTO input, BindingResult result) throws BussinesRuleValidationException, BussinesRuleException,BussinesRuleMovimientoValidationException {
        MovimientoResponse save = iMovimientoService.save(input, result);
        return ResponseEntity.ok(save);
    }
    
    @PutMapping("/movimientos/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody MovimientoDTO input, BindingResult result, @PathVariable Long id) throws BussinesRuleException, BussinesRuleValidationException {
        iMovimientoService.put(input, result, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    
    
    
    
}
