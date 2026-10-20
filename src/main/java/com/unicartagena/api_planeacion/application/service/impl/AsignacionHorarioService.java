package com.unicartagena.api_planeacion.application.service.impl;

import com.unicartagena.api_planeacion.application.dtos.AsignacionHorarioDTO;
import com.unicartagena.api_planeacion.application.entities.*;
import com.unicartagena.api_planeacion.application.repositories.*;
import com.unicartagena.api_planeacion.application.request.AsignacionHorarioRequestDTO;
import com.unicartagena.api_planeacion.application.service.IAsignacionHorarioService;
import com.unicartagena.api_planeacion.base.exceptions.NotFound;
import com.unicartagena.api_planeacion.base.exceptions.ScheduleCollisionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsignacionHorarioService implements IAsignacionHorarioService {

    private final AsignacionHorarioRepository asignacionRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final AulaRepository aulaRepository;
    private final HorarioRepository horarioRepository;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AsignacionHorarioDTO assign(AsignacionHorarioRequestDTO request) {

        Horario horario = horarioRepository.findById(request.getHorarioId())
                .orElseThrow(() -> new NotFound("Horario no encontrado con id: " + request.getHorarioId()));

        if (Boolean.TRUE.equals(horario.getEsReceso())) {
            throw new ScheduleCollisionException("Error: La franja " + horario.getNombre() + " está reservada para receso/mantenimiento.");
        }

        Asignatura asignatura = asignaturaRepository.findById(request.getAsignaturaId())
                .orElseThrow(() -> new NotFound("Asignatura no encontrada con id: " + request.getAsignaturaId()));

        Aula aula = aulaRepository.findById(request.getAulaId())
                .orElseThrow(() -> new NotFound("Aula no encontrada con id: " + request.getAulaId()));

        if (aula.getCapacidad() < asignatura.getCantidadMatriculados()) {
            throw new ScheduleCollisionException("Capacidad insuficiente: El aula " + aula.getCodigo() +
                    " (" + aula.getCapacidad() + " puestos) no soporta " +
                    asignatura.getCantidadMatriculados() + " estudiantes de " + asignatura.getNombre());
        }

        if (asignacionRepository.existsByAulaIdAndHorarioIdAndTipoSemana(aula.getId(), horario.getId(), request.getTipoSemana())) {
            throw new ScheduleCollisionException("Colisión de Aula (C_aula): El aula " + aula.getCodigo() +
                    " ya está ocupada en " + request.getTipoSemana() + " durante el bloque " + horario.getNombre());
        }

        if (asignacionRepository.existsBySemestreAndHorarioAndTipoSemana(asignatura.getSemestre().getId(), horario.getId(), request.getTipoSemana())) {
            throw new ScheduleCollisionException("Colisión de Semestre (C_semestre): El semestre " +
                    asignatura.getSemestre().getNumeroSemestre() + " ya tiene otra asignatura en el bloque " +
                    horario.getNombre() + " para " + request.getTipoSemana());
        }

        AsignacionHorario asignacion = AsignacionHorario.builder()
                .asignatura(asignatura)
                .aula(aula)
                .horario(horario)
                .tipoSemana(request.getTipoSemana())
                .build();

        return mapToDTO(asignacionRepository.save(asignacion));
    }

    @Override
    public List<AsignacionHorarioDTO> findAll() {
        return asignacionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!asignacionRepository.existsById(id)) {
            throw new NotFound("No existe la asignación con id: " + id);
        }
        asignacionRepository.deleteById(id);
    }

    private AsignacionHorarioDTO mapToDTO(AsignacionHorario a) {
        String franja = a.getHorario().getHoraInicio() + " - " + a.getHorario().getHoraFin();
        return new AsignacionHorarioDTO(
                a.getId(),
                a.getAsignatura().getId(),
                a.getAsignatura().getNombre(),
                a.getAula().getId(),
                a.getAula().getNombre(),
                a.getHorario().getId(),
                a.getHorario().getNombre(),
                franja,
                a.getTipoSemana()
        );
    }
}