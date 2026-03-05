package com.dev.backendapi.application.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String clienteid;
    private Boolean estado;
}