/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.dominio.repository;

import com.neoris.microservicio.dominio.entity.Cliente;
import com.neoris.microservicio.dominio.entity.Movimiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Santiago Betancur
 */
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query(value = "SELECT saldo FROM MOVIMIENTOS where ID_CLIENTE =?1 and ID_CUENTA =?2 order by ID_MOVIMIENTO desc limit 1", nativeQuery = true)
    public Double ultimoSaldo(Long idCliente, Long idCuenta);

    @Query("SELECT m FROM Movimiento m INNER JOIN Cliente c ON m.objClienteMovimiento = c INNER JOIN Cuenta ct ON m.objCuentaMovimiento = ct")
    public List<Movimiento> datosCliente();

}
