/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio;

/**
 *
 * @author Santiago Betancur
 */
public class MovimientoTest {

    private Long idMovimiento;
    private String fecha;
    private String tipoMovimiento;
    private String valor;
    private Double saldo = 5000.0;

    public MovimientoTest(Long idMovimiento, String fecha, String tipoMovimiento, String valor) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getValor() {
        return valor;
    }

    public Double getSaldo() {
        return saldo;
    }

    

    
}
