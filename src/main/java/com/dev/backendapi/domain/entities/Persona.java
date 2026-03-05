package com.dev.backendapi.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.io.Serializable;

/**
 * Entidad Base Persona.
 */
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String genero;

    @NotNull(message = "La edad es obligatoria")
    private Integer edad;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    private String direccion;
    private String telefono;
}
