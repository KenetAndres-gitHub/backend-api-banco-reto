package com.dev.backendapi;

import com.dev.backendapi.application.dto.ClienteRequestDTO;
import com.dev.backendapi.application.dto.ClienteResponseDTO;
import com.dev.backendapi.application.service.ClienteService;
import com.dev.backendapi.infrastructure.controller.ClienteController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void crearCliente_DatosValidos_Retorna201Created() throws Exception {
        // Arrange
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setNombre("Kenet Chungandro");
        request.setEdad(30);
        request.setIdentificacion("1234567890");
        request.setClienteid("kenet.c");
        request.setContrasena("1234");
        request.setEstado(true);

        ClienteResponseDTO response = ClienteResponseDTO.builder().clienteid("kenet.c").nombre("Kenet Chungandro").build();

        when(clienteService.crearCliente(any(ClienteRequestDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
