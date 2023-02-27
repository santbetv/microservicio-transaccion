/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.dominio.dtos;

import com.neoris.microservicio.dominio.entity.Movimiento;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Santiago Betancur
 */
@Getter
@Setter
public class MovimientoResponse {

    private String fecha;
    private String nombre;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Double tipoMovimiento;
    private Double saldo;

    public MovimientoResponse() {
    }

    public MovimientoResponse(Movimiento movimiento) {
        this.fecha = movimiento.getFecha().toString();
        this.nombre = movimiento.getObjCuentaMovimiento().getObjCliente().getNombre();
        this.numeroCuenta = movimiento.getObjCuentaMovimiento().getNumeroNuenta();
        this.tipoCuenta = movimiento.getTipoMovimiento();
        this.saldoInicial = movimiento.getObjCuentaMovimiento().getSaldoInicial();
        this.estado = movimiento.getObjCuentaMovimiento().getEstado();
        this.tipoMovimiento = movimiento.getValor();
        this.saldo = movimiento.getSaldo();
    }

    public MovimientoResponse build(Movimiento movimiento) {
        return new MovimientoResponse(movimiento);
    }

}
