package com.dev.backendapi.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CuentaResponseDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private String clienteNombre; // Solo exponemos el nombre, no la entidad completa
}