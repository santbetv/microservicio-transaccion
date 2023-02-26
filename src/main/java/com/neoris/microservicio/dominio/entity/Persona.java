package com.neoris.microservicio.dominio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Persona implements Serializable  {
	
	private String nombre;
	private String genero;
	private String edad;
	private String identificacion;
	private String direccion;
	private String telefono;
	
	private static final long serialVersionUID = -4658598829300825501L;
	
}
