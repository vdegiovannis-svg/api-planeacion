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
                    Horario.builder().nombre("BLOQUE_01").horaInicio(LocalTime.of(6, 30)).horaFin(LocalTime.of(8, 10)).esReceso(false).build(),
                    Horario.builder().nombre("BLOQUE_02").horaInicio(LocalTime.of(8, 10)).horaFin(LocalTime.of(9, 50)).esReceso(false).build(),
                    Horario.builder().nombre("BLOQUE_03").horaInicio(LocalTime.of(9, 50)).horaFin(LocalTime.of(11, 30)).esReceso(false).build(),
                    Horario.builder().nombre("RECESO_ALMUERZO").horaInicio(LocalTime.of(11, 30)).horaFin(LocalTime.of(12, 30)).esReceso(true).build(),
                    Horario.builder().nombre("BLOQUE_04").horaInicio(LocalTime.of(12, 30)).horaFin(LocalTime.of(14, 10)).esReceso(false).build(),
                    Horario.builder().nombre("BLOQUE_05").horaInicio(LocalTime.of(14, 10)).horaFin(LocalTime.of(15, 50)).esReceso(false).build(),
                    Horario.builder().nombre("BLOQUE_06").horaInicio(LocalTime.of(15, 50)).horaFin(LocalTime.of(17, 30)).esReceso(false).build()
            );
            horarioRepository.saveAll(bloques);
        }
    }
}