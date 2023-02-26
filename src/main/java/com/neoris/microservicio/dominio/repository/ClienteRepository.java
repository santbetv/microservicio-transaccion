package com.neoris.microservicio.dominio.repository;

import com.neoris.microservicio.dominio.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
*
* @author Santiago Betancur
*/
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
