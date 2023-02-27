/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.dominio.repository;

import com.neoris.microservicio.dominio.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rizzoli
 */
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

//    @Query("SELECT m FROM Movimiento m WHERE m.idMovimiento =:idMovimiento")
//        @Query("SELECT m, oc FROM Movimiento m INNER JOIN m.objClienteMovimiento oc")
//    @Query("SELECT m FROM Movimiento m INNER JOIN m.Cliente c WHERE c.idCliente =:idCliente")
//    @Query(value = "SELECT FECHA as fecha FROM movimientos m INNER JOIN clientes c ON m.id_cliente = c.id_cliente INNER JOIN cuentas ct ON m.id_cliente = ct.id_cliente WHERE m.id_movimiento = :id", nativeQuery = true)
    @Query(value = "SELECT saldo FROM MOVIMIENTOS where ID_CLIENTE =1 and ID_CUENTA =1 order by ID_MOVIMIENTO desc limit 1", nativeQuery = true)
    public Double ultimoSaldo(Long idCliente, Long idCuenta);

}
