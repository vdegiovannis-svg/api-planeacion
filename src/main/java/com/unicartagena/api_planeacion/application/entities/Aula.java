package com.unicartagena.api_planeacion.application.entities;

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
@Table(name = "aulas")

public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(nullable = false, length = 60)
    private String nombre;

    public enum UbicacionAula {
        Piso_1("Aulas 101-104"),
        Piso_2("Aulas 201-204"),
        Piso_3("Aulas 301-304, 308-309"),
        Piso_Informatica("Sala 1, Sala 2, Sala 3"),
        Piso_4("Aulas 401-405"),
        Piso_5("Aulas 501-503"),
        Piso_Biblioteca("Sala IDEAS");

        private final String descripcion;

        UbicacionAula(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    public enum TipoAula {
        Estandar,
        Sala_Informatica,
        Aula_Hibrida,
        Aula_Idea
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAula tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UbicacionAula ubicacion;

    @Column(nullable = false)
    private Integer capacidad;

}
