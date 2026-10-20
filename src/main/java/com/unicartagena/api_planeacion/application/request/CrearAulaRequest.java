package com.unicartagena.api_planeacion.application.request;

import com.unicartagena.api_planeacion.application.entities.Aula;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CrearAulaRequest {
    @NotBlank(message = "El código del aula es obligatorio")
    @Size(max = 30, message = "El código no debe superar 30 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre del aula es obligatorio")
    @Size(max = 60, message = "El nombre no debe superar 60 caracteres")
    private String nombre;

    @NotNull(message = "La ubicación es obligatoria")
    private Aula.UbicacionAula ubicacion;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad mínima debe ser 1")
    private Integer capacidad;

    @NotNull(message = "El tipo de aula es obligatorio")
    private Aula.TipoAula tipo;
}
