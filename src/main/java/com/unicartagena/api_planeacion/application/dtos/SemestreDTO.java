package com.unicartagena.api_planeacion.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SemestreDTO {
    private Long id;
    private String codigo;
    private Integer numeroSemestre;
    private Long programaId;
    private String programaNombre;

}
