package com.unicartagena.api_planeacion.application.repositories;

import com.unicartagena.api_planeacion.application.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByEsRecesoFalse();
}