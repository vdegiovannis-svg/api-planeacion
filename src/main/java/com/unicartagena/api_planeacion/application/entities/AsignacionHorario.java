package com.unicartagena.api_planeacion.application.entities;

import com.unicartagena.api_planeacion.application.entities.enums.TipoSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asignaciones_horario",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_aula_horario_semana", columnNames = {"aula_id", "horario_id", "tipo_semana"}),
                @UniqueConstraint(name = "uk_asignatura_horario_semana", columnNames = {"asignatura_id", "horario_id", "tipo_semana"})
        }
)
public class AsignacionHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "horario_id", nullable = false)
    private Horario horario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_semana", nullable = false, length = 15)
    private TipoSemana tipoSemana;
}