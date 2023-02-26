package com.neoris.microservicio.dominio.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO extends PersonaDTO {

    @NotEmpty(message = "no puede estar vacio")
    @Size(min = 2, max = 12, message = "el tamaño debe de estar entre 2 y 12")
    @Column(nullable = false)
    private String contrasena;

    private Boolean estado;
}
