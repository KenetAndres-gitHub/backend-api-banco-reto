package com.dev.backendapi.infrastructure.controller;

import com.dev.backendapi.application.service.MovimientoService;
import com.dev.backendapi.domain.entities.Movimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    /**
     * Endpoint para registrar un nuevo movimiento.
     * Requerimiento F2 y F3 del PDF.
     */
    @PostMapping
    public ResponseEntity<?> registrarMovimiento(@RequestBody Map<String, Object> request) {
        String numeroCuenta = (String) request.get("numeroCuenta");
        // Convertimos el valor entrante a BigDecimal para evitar pérdida de precisión
        BigDecimal valor = new BigDecimal(request.get("valor").toString());

        Movimiento movimiento = movimientoService.registrarMovimiento(numeroCuenta, valor);

        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }
}