package com.dev.backendapi.application.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MovimientoResponseDTO {
    private String numeroCuenta;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldoDisponible;
    private LocalDateTime fecha;
}