package com.unicartagena.api_planeacion.application.dtos;

import com.unicartagena.api_planeacion.application.entities.Aula;
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
    private Aula.UbicacionAula ubicacion;
    private Integer capacidad;
    private Aula.TipoAula tipo;

}
