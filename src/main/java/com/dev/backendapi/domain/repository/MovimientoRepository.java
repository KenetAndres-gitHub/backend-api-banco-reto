package com.dev.backendapi.domain.repository;

import com.dev.backendapi.domain.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    /**
     * Consulta personalizada (JPQL) para obtener todos los movimientos de un cliente
     * en un rango de fechas específico.
     */
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.cliente.clienteid = :clienteid " +
            "AND m.fecha >= :fechaInicio AND m.fecha <= :fechaFin " +
            "ORDER BY m.fecha DESC")
    List<Movimiento> findMovimientosByClienteAndFechaRango(
            @Param("clienteid") String clienteid,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);
}