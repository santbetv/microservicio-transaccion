package com.neoris.microservicio.dominio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movimientos")
public class Movimiento implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6146988320602532496L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long idMovimiento;

    private LocalDate fecha;

    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    private Double valor;
    private Double saldo = 0.0;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente objClienteMovimiento;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta")
    private Cuenta objCuentaMovimiento;

    @PrePersist
    private void guardarFechaPreviamente() {
        this.fecha = LocalDate.now();
    }

    public Double agregarSaldoTotal(Double saldoInicial, String claseMovimiento, Double saldoaAnterior) {

        if (TipoMovimiento.DEPOSITO.getValor().equals(claseMovimiento.toUpperCase())) {
            if (saldoaAnterior != null) {
                saldoInicial = saldoaAnterior;
            }
            this.saldo = saldoInicial + this.valor;
        } else {
            actualizarPostSaldoTotal(saldoaAnterior,saldoInicial);
        }

        return this.saldo;
    }

    public Double actualizarPostSaldoTotal(Double saldoaAnterior,Double saldoInicial) {
        if (saldoaAnterior != null) {
            this.saldo = saldoaAnterior;
            if (TipoMovimiento.RETIRO.getValor().equals(this.tipoMovimiento.toUpperCase())) {
                this.saldo = this.saldo - this.valor;
            }
        }else{
            this.saldo = saldoInicial - this.valor;
        }

        return this.saldo;
    }

    public Double actualizarSaldoTotal() {
        if (TipoMovimiento.DEPOSITO.getValor().equals(this.tipoMovimiento.toUpperCase())) {
            this.saldo = this.saldo + this.valor;
        } else if (TipoMovimiento.RETIRO.getValor().equals(this.tipoMovimiento.toUpperCase())) {
            this.saldo = this.saldo - this.valor;
        }
        return this.saldo;
    }

}
