package com.unicartagena.api_planeacion.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private Integer cantidadMatriculados;
    private String elementosRequeridos;
    private Long semestreId;
    private String semestreCodigo;
    private Integer numeroSemestre;
}
