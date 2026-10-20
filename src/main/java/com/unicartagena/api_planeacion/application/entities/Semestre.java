package com.unicartagena.api_planeacion.application.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "semestres")

public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String codigo; // Ej: "SEM-IS-1" (Código único para consulta)

    @Min(1)
    @Max(12)
    @Column(name = "numero_semestre", nullable = false)
    private Integer numeroSemestre; // Ej: 1, 2, 3...

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "programa_id", nullable = false)
    private Programa programa;
}
