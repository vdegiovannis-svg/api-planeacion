package com.unicartagena.api_planeacion.application.repositories;

import com.unicartagena.api_planeacion.application.entities.AsignacionHorario;
import com.unicartagena.api_planeacion.application.entities.enums.TipoSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionHorarioRepository extends JpaRepository<AsignacionHorario, Long> {

    boolean existsByAulaIdAndHorarioIdAndTipoSemana(Long aulaId, Long horarioId, TipoSemana tipoSemana);

    boolean existsByAsignaturaIdAndHorarioIdAndTipoSemana(Long asignaturaId, Long horarioId, TipoSemana tipoSemana);

    @Query("SELECT COUNT(a) > 0 FROM AsignacionHorario a " +
            "WHERE a.asignatura.semestre.id = :semestreId " +
            "AND a.horario.id = :horarioId " +
            "AND a.tipoSemana = :tipoSemana")
    boolean existsBySemestreAndHorarioAndTipoSemana(
            @Param("semestreId") Long semestreId,
            @Param("horarioId") Long horarioId,
            @Param("tipoSemana") TipoSemana tipoSemana
    );

    List<AsignacionHorario> findByAulaId(Long aulaId);
}