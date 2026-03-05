package com.dev.backendapi.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Entidad Cliente. Hereda de Persona.
 */
@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Persona {
    @Column(unique = true, nullable = false)
    @NotBlank(message = "El ID de cliente es obligatorio")
    private String clienteid;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    private Boolean estado;
}
