package com.dev.backendapi.infrastructure.controller;

import com.dev.backendapi.application.dto.ClienteRequestDTO;
import com.dev.backendapi.application.dto.ClienteResponseDTO;
import com.dev.backendapi.application.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{clienteid}")
    public ResponseEntity<ClienteResponseDTO> obtenerCliente(@PathVariable String clienteid) {
        return ResponseEntity.ok(clienteService.obtenerCliente(clienteid));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@Valid @RequestBody ClienteRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(request));
    }

    @PutMapping("/{clienteid}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable String clienteid, @Valid @RequestBody ClienteRequestDTO request) {
        return ResponseEntity.ok(clienteService.actualizarCliente(clienteid, request));
    }

    @DeleteMapping("/{clienteid}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String clienteid) {
        clienteService.eliminarCliente(clienteid);
        return ResponseEntity.noContent().build();
    }
}