package com.unicartagena.api_planeacion.application.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearAsignaturaRequest {
    @NotBlank(message = "El código de la asignatura es obligatorio")
    @Size(max = 15, message = "El código no debe superar 15 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre de la asignatura es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar 100 caracteres")
    private String nombre;

    @NotNull(message = "La cantidad de matriculados es obligatoria")
    @Min(value = 1, message = "Debe haber al menos 1 matriculado")
    private Integer cantidadMatriculados;

    @Size(max = 255, message = "Los elementos requeridos no deben superar 255 caracteres")
    private String elementosRequeridos;

    @NotNull(message = "El ID del semestre es obligatorio")
    private Long semestreId;
}
