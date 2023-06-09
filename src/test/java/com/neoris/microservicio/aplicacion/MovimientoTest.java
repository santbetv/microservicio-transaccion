/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.aplicacion;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Santiago Betancur
 */
@Getter
@Setter
public class MovimientoTest {

    private Long idMovimiento;
    private String fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo = 5000.0;

    public MovimientoTest(Long idMovimiento, String fecha, String tipoMovimiento, Double valor) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
    }

    public MovimientoTest() {
    }
    
}

