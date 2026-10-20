package com.unicartagena.api_planeacion.base.configs;

import com.unicartagena.api_planeacion.application.entities.Horario;
import com.unicartagena.api_planeacion.application.repositories.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HorarioInitializer implements CommandLineRunner {
    private final HorarioRepository horarioRepository;

    @Override
    public void run(String... args) {
        if (horarioRepository.count() == 0) {
            List<Horario> bloques = List.of(
                    Horario.builder().nombre("BLOQUE_01").horaInicio(LocalTime.of(6, 30)).horaFin(LocalTime.of(8, 0)).esReceso(false).build(),
                    Horario.builder().nombre("BLOQUE_02").horaInicio(LocalTime.of(8, 0)).horaFin(LocalTime.of(9, 30)).esReceso(false).build(),
                    Horario.builder().nombre("BLOQUE_03").horaInicio(LocalTime.of(9, 30)).horaFin(LocalTime.of(11, 0)).esReceso(false).build(),
                    Horario.builder().nombre("BUFFER_MAÑANA").horaInicio(LocalTime.of(11, 0)).horaFin(LocalTime.of(11, 30)).esReceso(true).build(),
                    Horario.builder().nombre("RECESO_ALMUERZO").horaInicio(LocalTime.of(11, 30)).horaFin(LocalTime.of(12, 30)).esReceso(true).build(),
                    Horario.builder().nombre("BLOQUE_04").horaInicio(LocalTime.of(12, 30)).horaFin(LocalTime.of(14, 0)).esReceso(false).build(),
                    Horario.builder().nombre("BLOQUE_05").horaInicio(LocalTime.of(14, 0)).horaFin(LocalTime.of(15, 30)).esReceso(false).build(),
                    Horario.builder().nombre("BLOQUE_06").horaInicio(LocalTime.of(15, 30)).horaFin(LocalTime.of(17, 0)).esReceso(false).build(),
                    Horario.builder().nombre("BUFFER_TARDE").horaInicio(LocalTime.of(17, 0)).horaFin(LocalTime.of(17, 30)).esReceso(true).build()
            );
            horarioRepository.saveAll(bloques);
        }
    }
}