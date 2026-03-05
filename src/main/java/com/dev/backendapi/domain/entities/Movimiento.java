package com.dev.backendapi.domain.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad Movimientos.
 */
@Entity
@Table(name = "movimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha;

    private String tipoMovimiento;

    @Column(precision = 18, scale = 2)
    private BigDecimal valor;

    @Column(precision = 18, scale = 2)
    private BigDecimal saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    @PrePersist
    protected void onCreate() {
        fecha = LocalDateTime.now();
    }
}