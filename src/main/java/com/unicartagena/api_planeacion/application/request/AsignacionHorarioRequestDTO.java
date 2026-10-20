package com.unicartagena.api_planeacion.application.request;

import com.unicartagena.api_planeacion.application.entities.enums.TipoSemana;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignacionHorarioRequestDTO {

    @NotNull(message = "El ID de la asignatura es obligatorio")
    private Long asignaturaId;

    @NotNull(message = "El ID del aula es obligatorio")
    private Long aulaId;

    @NotNull(message = "El ID del horario es obligatorio")
    private Long horarioId;

    @NotNull(message = "El tipo de semana (SEMANA_A o SEMANA_B) es obligatorio")
    private TipoSemana tipoSemana;
}