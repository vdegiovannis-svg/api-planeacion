package com.unicartagena.api_planeacion.application.repositories;

import com.unicartagena.api_planeacion.application.entities.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long> {
    boolean existsByCodigo(String codigo);
}