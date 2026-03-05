package com.dev.backendapi.application.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object para Movimientos.
 */
@Data
public class MovimientoDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private BigDecimal valor;
    private BigDecimal saldoDisponible;
    private LocalDateTime fecha;
}