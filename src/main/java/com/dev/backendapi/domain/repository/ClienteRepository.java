package com.dev.backendapi.domain.repository;
import com.dev.backendapi.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio para la entidad Cliente.
 * Gestiona la persistencia de los datos de usuario y cliente.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca un cliente por su identificador de negocio único.
     * @param clienteid Identificador único del cliente.
     * @return Un Optional que contiene al cliente si existe.
     */
    Optional<Cliente> findByClienteid(String clienteid);
}