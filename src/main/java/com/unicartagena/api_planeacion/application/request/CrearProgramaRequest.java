package com.unicartagena.api_planeacion.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearProgramaRequest {

    @NotBlank(message = "El código del programa es obligatorio")
    @Size(max = 30, message = "El código no debe superar 30 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre del programa es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar 100 caracteres")
    private String nombre;

    @NotNull(message = "El ID de la facultad es obligatorio")
    private Long facultadId;
}