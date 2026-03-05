package com.dev.backendapi.application.service;

import com.dev.backendapi.application.dto.ReporteResponseDTO;
import com.dev.backendapi.domain.entities.Movimiento;
import com.dev.backendapi.domain.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final MovimientoRepository movimientoRepository;

    @Transactional(readOnly = true)
    public List<ReporteResponseDTO> generarReporte(String clienteid, LocalDate fechaInicio, LocalDate fechaFin) {

        // Ajustamos las fechas para que abarquen todo el día (desde las 00:00:00 hasta las 23:59:59)
        LocalDateTime inicio = fechaInicio.atStartOfDay();
        LocalDateTime fin = fechaFin.atTime(LocalTime.MAX);

        List<Movimiento> movimientos = movimientoRepository.findMovimientosByClienteAndFechaRango(clienteid, inicio, fin);

        return movimientos.stream().map(m -> ReporteResponseDTO.builder()
                .fecha(m.getFecha())
                .cliente(m.getCuenta().getCliente().getNombre())
                .numeroCuenta(m.getCuenta().getNumeroCuenta())
                .tipo(m.getCuenta().getTipoCuenta())
                // El saldo inicial en el momento del movimiento se calcula restando el valor del movimiento al saldo final
                .saldoInicial(m.getSaldo().subtract(m.getValor()))
                .estado(m.getCuenta().getEstado())
                .movimiento(m.getValor())
                .saldoDisponible(m.getSaldo())
                .build()
        ).collect(Collectors.toList());
    }
}