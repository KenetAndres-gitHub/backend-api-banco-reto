package com.dev.backendapi.infrastructure.controller;

import com.dev.backendapi.application.dto.CuentaRequestDTO;
import com.dev.backendapi.application.dto.CuentaResponseDTO;
import com.dev.backendapi.application.service.CuentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<CuentaResponseDTO>> listarCuentas() {
        return ResponseEntity.ok(cuentaService.listarCuentas());
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaResponseDTO> obtenerCuenta(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(cuentaService.obtenerCuenta(numeroCuenta));
    }

    @PostMapping
    public ResponseEntity<CuentaResponseDTO> crearCuenta(@Valid @RequestBody CuentaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.crearCuenta(request));
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaResponseDTO> actualizarCuenta(@PathVariable String numeroCuenta, @Valid @RequestBody CuentaRequestDTO request) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(numeroCuenta, request));
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable String numeroCuenta) {
        cuentaService.eliminarCuenta(numeroCuenta);
        return ResponseEntity.noContent().build();
    }
}