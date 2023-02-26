package com.neoris.microservicio.dominio.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cuentas")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta")
	private Long idCuenta;
	
	@Column(name = "numero_nuenta")
	private String numeroNuenta;
	
	@Column(name = "tipo_cuenta")
	private String tipoCuenta;
	
	@Column(name = "saldo_inicial")
	private Double saldoInicial;
	
	private Boolean estado;
        
	@JsonIgnoreProperties(value = { "cuentas", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente objCliente;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objCuentaMovimiento", cascade = CascadeType.ALL)
	private List<Movimiento> cuentaMovimientos;
	
	
	
	
}
