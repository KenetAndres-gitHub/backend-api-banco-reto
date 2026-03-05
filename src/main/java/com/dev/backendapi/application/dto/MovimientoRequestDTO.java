package com.dev.backendapi.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class MovimientoRequestDTO {
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String numeroCuenta;

    @NotNull(message = "El valor del movimiento es obligatorio")
    private BigDecimal valor;
}