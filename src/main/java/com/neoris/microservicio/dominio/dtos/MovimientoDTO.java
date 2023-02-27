/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.dominio.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Santiago Betancur
 */
@Getter
@Setter
public class MovimientoDTO {
    
    @Positive(message = "Debe ser numero positivo")
    private Long idCliente;
    @Positive(message = "Debe ser numero positivo")
    private Long idCuenta;
    @NotEmpty(message = "no puede estar vacio")
    private String tipoMovimiento;
    private Double valor;
}
