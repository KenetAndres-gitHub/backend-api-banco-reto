package com.dev.backendapi.domain.repository;

import com.dev.backendapi.domain.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio para la entidad Cuenta.
 * Permite realizar operaciones sobre las cuentas bancarias.
 */
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    /**
     * Busca una cuenta específica por su número de cuenta único.
     * @param numeroCuenta El número de cuenta a buscar.
     * @return Un Optional con la cuenta encontrada.
     */
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}