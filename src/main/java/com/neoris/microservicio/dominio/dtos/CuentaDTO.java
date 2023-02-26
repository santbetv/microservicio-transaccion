/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.dominio.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Santiago
 */
@Getter
@Setter
public class CuentaDTO {
    
    @NotEmpty(message = "no puede estar vacio")
    private String numeroNuenta;
    @NotEmpty(message = "no puede estar vacio")
    private String tipoCuenta;
    @Min(value = 1, message = "Mayor 0")
    private Double saldoInicial;
    private Boolean estado;
    @Positive(message = "Debe ser numero posotivo")
    private Long idCliente;

}
