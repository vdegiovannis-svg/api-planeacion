package com.unicartagena.api_planeacion.application.dtos;

import com.unicartagena.api_planeacion.application.entities.enums.TipoSemana;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionHorarioDTO {
    private Long id;
    private Long asignaturaId;
    private String asignaturaNombre;
    private Long aulaId;
    private String aulaNombre;
    private Long horarioId;
    private String horarioNombre;
    private String franjaHoraria;
    private TipoSemana tipoSemana;
}