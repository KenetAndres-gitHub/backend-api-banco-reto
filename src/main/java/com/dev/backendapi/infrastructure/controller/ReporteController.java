package com.dev.backendapi.infrastructure.controller;

import com.dev.backendapi.application.dto.ReporteResponseDTO;
import com.dev.backendapi.application.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    /**
     * Endpoint para generar el estado de cuenta.
     * Ejemplo de uso: /api/reportes?fechaInicio=2024-02-01&fechaFin=2024-02-29&cliente=jose.lema
     */
    @GetMapping
    public ResponseEntity<List<ReporteResponseDTO>> generarReporte(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam("cliente") String clienteid) {

        List<ReporteResponseDTO> reporte = reporteService.generarReporte(clienteid, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
}