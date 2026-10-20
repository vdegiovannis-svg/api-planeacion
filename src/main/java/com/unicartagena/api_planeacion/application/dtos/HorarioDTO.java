package com.unicartagena.api_planeacion.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioDTO {
    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean esReceso;
    private String nombre;
}
