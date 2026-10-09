package com.unicartagena.api_planeacion.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AulaDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private String descripcion;

}
