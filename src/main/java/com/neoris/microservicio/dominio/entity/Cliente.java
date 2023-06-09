package com.neoris.microservicio.dominio.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name = "clientes")
public class Cliente extends Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    private String contrasena;

    private Boolean estado;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objCliente", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objClienteMovimiento", cascade = CascadeType.ALL)
    private List<Movimiento> clienteMovimientos;

    public Cliente() {
        this.cuentas = new ArrayList<>();
        this.clienteMovimientos = new ArrayList<>();
    }

    private static final long serialVersionUID = 4263263470080398951L;

}
