/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.dominio.repository;

import com.neoris.microservicio.dominio.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Santiago Betancur
 */
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    
}
