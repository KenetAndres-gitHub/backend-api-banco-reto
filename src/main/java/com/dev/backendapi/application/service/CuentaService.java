package com.dev.backendapi.application.service;

import com.dev.backendapi.application.dto.CuentaRequestDTO;
import com.dev.backendapi.application.dto.CuentaResponseDTO;
import com.dev.backendapi.domain.entities.Cliente;
import com.dev.backendapi.domain.entities.Cuenta;
import com.dev.backendapi.domain.repository.CuentaRepository;
import com.dev.backendapi.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteService clienteService;

    @Transactional(readOnly = true)
    public List<CuentaResponseDTO> listarCuentas() {
        return cuentaRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CuentaResponseDTO obtenerCuenta(String numeroCuenta) {
        Cuenta cuenta = obtenerCuentaEntidad(numeroCuenta);
        return mapearAResponse(cuenta);
    }

    /**
     * Método de uso interno para obtener la entidad JPA real.
     * Útil para cuando MovimientoService necesite la cuenta.
     */
    @Transactional(readOnly = true)
    protected Cuenta obtenerCuentaEntidad(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada: " + numeroCuenta));
    }

    @Transactional
    public CuentaResponseDTO crearCuenta(CuentaRequestDTO request) {
        Cliente cliente = clienteService.obtenerClienteEntidad(request.getClienteid());

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(request.getNumeroCuenta());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setSaldoInicial(request.getSaldoInicial());
        cuenta.setEstado(true);
        cuenta.setCliente(cliente);

        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        return mapearAResponse(cuentaGuardada);
    }

    @Transactional
    public CuentaResponseDTO actualizarCuenta(String numeroCuenta, CuentaRequestDTO request) {
        Cuenta cuentaExistente = obtenerCuentaEntidad(numeroCuenta);

        cuentaExistente.setTipoCuenta(request.getTipoCuenta());
        if (request.getEstado() != null) {
            cuentaExistente.setEstado(request.getEstado());
        }
        // El saldo inicial y el número de cuenta no se actualizan por temas de auditoría

        Cuenta cuentaActualizada = cuentaRepository.save(cuentaExistente);
        return mapearAResponse(cuentaActualizada);
    }

    @Transactional
    public void eliminarCuenta(String numeroCuenta) {
        Cuenta cuentaExistente = obtenerCuentaEntidad(numeroCuenta);
        // Eliminación Lógica
        cuentaExistente.setEstado(false);
        cuentaRepository.save(cuentaExistente);
    }

    /**
     * Mapper manual para convertir Entidad a DTO de Respuesta.
     */
    private CuentaResponseDTO mapearAResponse(Cuenta cuenta) {
        CuentaResponseDTO dto = new CuentaResponseDTO();
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.getEstado());
        if (cuenta.getCliente() != null) {
            dto.setClienteNombre(cuenta.getCliente().getNombre());
        }
        return dto;
    }
}