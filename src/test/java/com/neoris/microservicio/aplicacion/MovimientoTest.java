/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.aplicacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Santiago Betancur
 */
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoTest {

    private Long idMovimiento;
    private String fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo = 5000.0;
    
    
    public MovimientoTest builderMovimientoTest(Long idMovimiento, String fecha, String tipoMovimiento, Double valor){
        return new MovimientoTest(idMovimiento, fecha, tipoMovimiento, valor, 600000.0);
       
    } 
    
}

