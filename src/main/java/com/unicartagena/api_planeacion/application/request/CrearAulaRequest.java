package com.unicartagena.api_planeacion.application.request;

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

    @NotBlank(message = "La ubicación es obligatoria")
    @Size(max = 100, message = "La ubicación no debe superar 100 caracteres")
    private String ubicacion;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad mínima debe ser 1")
    private Integer capacidad;

    @Size(max = 255, message = "La descripción no debe superar 255 caracteres")
    private String descripcion;
}
