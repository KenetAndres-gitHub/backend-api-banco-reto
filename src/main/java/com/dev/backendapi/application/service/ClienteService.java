package com.dev.backendapi.application.service;

import com.dev.backendapi.application.dto.ClienteRequestDTO;
import com.dev.backendapi.application.dto.ClienteResponseDTO;
import com.dev.backendapi.domain.entities.Cliente;
import com.dev.backendapi.domain.repository.ClienteRepository;
import com.dev.backendapi.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerCliente(String clienteid) {
        Cliente cliente = obtenerClienteEntidad(clienteid);
        return mapearAResponse(cliente);
    }

    @Transactional(readOnly = true)
    public Cliente obtenerClienteEntidad(String clienteid) {
        return clienteRepository.findByClienteid(clienteid)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteid));
    }

    @Transactional
    public ClienteResponseDTO crearCliente(ClienteRequestDTO request) {
        Cliente cliente = new Cliente();
        mapearAEntidad(request, cliente);
        cliente.setEstado(true);
        // Aquí se encriptará la contraseña en el futuro
        cliente.setContrasena(request.getContrasena());

        Cliente clienteGuardado = clienteRepository.save(cliente);
        return mapearAResponse(clienteGuardado);
    }

    @Transactional
    public ClienteResponseDTO actualizarCliente(String clienteid, ClienteRequestDTO request) {
        Cliente clienteExistente = obtenerClienteEntidad(clienteid);

        mapearAEntidad(request, clienteExistente);
        if (request.getEstado() != null) {
            clienteExistente.setEstado(request.getEstado());
        }

        Cliente clienteActualizado = clienteRepository.save(clienteExistente);
        return mapearAResponse(clienteActualizado);
    }

    @Transactional
    public void eliminarCliente(String clienteid) {
        Cliente clienteExistente = obtenerClienteEntidad(clienteid);
        clienteExistente.setEstado(false);
        clienteRepository.save(clienteExistente);
    }

    private void mapearAEntidad(ClienteRequestDTO dto, Cliente entidad) {
        entidad.setNombre(dto.getNombre());
        entidad.setGenero(dto.getGenero());
        entidad.setEdad(dto.getEdad());
        entidad.setIdentificacion(dto.getIdentificacion());
        entidad.setDireccion(dto.getDireccion());
        entidad.setTelefono(dto.getTelefono());
        entidad.setClienteid(dto.getClienteid());
        if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
            entidad.setContrasena(dto.getContrasena());
        }
    }

    private ClienteResponseDTO mapearAResponse(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setNombre(cliente.getNombre());
        dto.setGenero(cliente.getGenero());
        dto.setEdad(cliente.getEdad());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        dto.setClienteid(cliente.getClienteid());
        dto.setEstado(cliente.getEstado());
        return dto;
    }
}