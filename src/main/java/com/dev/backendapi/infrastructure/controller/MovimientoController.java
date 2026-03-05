package com.dev.backendapi.infrastructure.controller;

import com.dev.backendapi.application.dto.MovimientoRequestDTO;
import com.dev.backendapi.application.dto.MovimientoResponseDTO;
import com.dev.backendapi.application.service.MovimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoResponseDTO> registrarMovimiento(@Valid @RequestBody MovimientoRequestDTO request) {
        MovimientoResponseDTO response = movimientoService.registrarMovimiento(
                request.getNumeroCuenta(),
                request.getValor()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}