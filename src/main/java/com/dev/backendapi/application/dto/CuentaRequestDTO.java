package com.dev.backendapi.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CuentaRequestDTO {
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String tipoCuenta;

    @NotNull(message = "El saldo inicial es obligatorio")
    private BigDecimal saldoInicial;

    @NotBlank(message = "El ID del cliente es obligatorio")
    private String clienteid;

    private Boolean estado;
}