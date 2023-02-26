package com.neoris.microservicio.dominio.dtos;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {
	
	@NotEmpty(message = "no puede estar vacio")
	private String nombre;
	@NotEmpty(message = "no puede estar vacio")
	private String genero;
	@NotEmpty(message = "no puede estar vacio")
	private String edad;
	@NotEmpty(message = "no puede estar vacio")
	private String identificación;
	private String direccion;
	private String telefono;	
}
