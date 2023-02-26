package com.neoris.microservicio.dominio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class MovimientoPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5598460983055790741L;

	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "id_cuenta")
	private Long idCuenta;

}
