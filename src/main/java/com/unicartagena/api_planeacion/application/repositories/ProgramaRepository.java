package com.unicartagena.api_planeacion.application.repositories;

import com.unicartagena.api_planeacion.application.entities.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    boolean existsByCodigo(String codigo);
    List<Programa> findByFacultadId(Long facultadId);
}