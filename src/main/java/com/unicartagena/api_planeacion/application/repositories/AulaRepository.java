package com.unicartagena.api_planeacion.application.repositories;

import com.unicartagena.api_planeacion.application.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AulaRepository extends JpaRepository<Aula, Long> {
    boolean existsByCodigo(String codigo);
    Optional<Aula> findByCodigo(String codigo);
}
