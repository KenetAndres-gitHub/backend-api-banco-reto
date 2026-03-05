package com.dev.backendapi;

import com.dev.backendapi.application.service.MovimientoService;
import com.dev.backendapi.domain.entities.Cuenta;
import com.dev.backendapi.domain.repository.CuentaRepository;
import com.dev.backendapi.domain.repository.MovimientoRepository;
import com.dev.backendapi.infrastructure.exception.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private MovimientoService movimientoService;

    private Cuenta cuentaMock;

    @BeforeEach
    void setUp() {
        cuentaMock = new Cuenta();
        cuentaMock.setNumeroCuenta("478758");
        cuentaMock.setSaldoInicial(new BigDecimal("100.00"));
        cuentaMock.setEstado(true);
    }

    @Test
    void registrarMovimiento_RetiroExcedeSaldo_LanzaExcepcion() {
        // Arrange (Preparar)
        when(cuentaRepository.findByNumeroCuenta("478758")).thenReturn(Optional.of(cuentaMock));
        BigDecimal retiroInvalido = new BigDecimal("-150.00");

        // Act & Assert (Ejecutar y Validar)
        InsufficientBalanceException exception = assertThrows(
                InsufficientBalanceException.class,
                () -> movimientoService.registrarMovimiento("478758", retiroInvalido)
        );

        assertEquals("Saldo no disponible", exception.getMessage());
    }
}