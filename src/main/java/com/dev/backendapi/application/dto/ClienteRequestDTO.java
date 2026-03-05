package com.dev.backendapi.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    private String genero;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "El cliente debe ser mayor de edad (mínimo 18 años)")
    private Integer edad;

    @NotBlank(message = "La identificación es obligatoria")
    @Size(min = 10, max = 13, message = "La identificación debe tener entre 10 y 13 caracteres")
    private String identificacion;

    private String direccion;

    private String telefono;

    @NotBlank(message = "El ID de cliente es obligatorio")
    private String clienteid;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    private Boolean estado;
}