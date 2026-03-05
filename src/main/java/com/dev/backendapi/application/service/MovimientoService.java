package com.dev.backendapi.application.service;

import com.dev.backendapi.domain.entities.Cuenta;
import com.dev.backendapi.domain.entities.Movimiento;
import com.dev.backendapi.domain.repository.CuentaRepository;
import com.dev.backendapi.domain.repository.MovimientoRepository;
import com.dev.backendapi.infrastructure.exception.InsufficientBalanceException;
import com.dev.backendapi.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Transactional
    public Movimiento registrarMovimiento(String numeroCuenta, BigDecimal valor) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(valor);

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValor(valor);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setTipoMovimiento(valor.compareTo(BigDecimal.ZERO) > 0 ? "Deposito" : "Retiro");

        return movimientoRepository.save(movimiento);
    }
}