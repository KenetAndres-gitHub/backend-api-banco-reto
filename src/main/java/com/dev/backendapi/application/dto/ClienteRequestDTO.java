package com.dev.backendapi.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String genero;

    @NotNull(message = "La edad es obligatoria")
    private Integer edad;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    private String direccion;
    private String telefono;

    @NotBlank(message = "El ID de cliente es obligatorio")
    private String clienteid;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    private Boolean estado;
}