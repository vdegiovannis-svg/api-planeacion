package com.unicartagena.api_planeacion.application.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CrearSemestreRequest {
    @NotBlank(message = "El código del semestre es obligatorio")
    @Size(max = 30, message = "El código no debe superar 30 caracteres")
    private String codigo;

    @NotNull(message = "El número de semestre es obligatorio")
    @Min(value = 1, message = "El semestre mínimo es 1")
    @Max(value = 10, message = "El semestre máximo es 12")
    private Integer numeroSemestre;

    @NotNull(message = "El ID del programa es obligatorio")
    private Long programaId;
}
