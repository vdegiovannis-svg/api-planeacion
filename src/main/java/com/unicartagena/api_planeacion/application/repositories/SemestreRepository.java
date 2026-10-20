package com.unicartagena.api_planeacion.application.repositories;

import com.unicartagena.api_planeacion.application.entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
    boolean existsByCodigo(String codigo);
    List<Semestre> findByProgramaId(Long programaId);
}
