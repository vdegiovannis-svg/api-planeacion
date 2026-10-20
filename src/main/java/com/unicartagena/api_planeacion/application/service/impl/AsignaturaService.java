package com.unicartagena.api_planeacion.application.service.impl;

import com.unicartagena.api_planeacion.application.dtos.AsignaturaDTO;
import com.unicartagena.api_planeacion.application.entities.Asignatura;
import com.unicartagena.api_planeacion.application.entities.Semestre;
import com.unicartagena.api_planeacion.application.repositories.AsignaturaRepository;
import com.unicartagena.api_planeacion.application.repositories.SemestreRepository;
import com.unicartagena.api_planeacion.application.request.CrearAsignaturaRequest;
import com.unicartagena.api_planeacion.application.service.IAsignaturaService;
import com.unicartagena.api_planeacion.base.exceptions.AlreadyExists;
import com.unicartagena.api_planeacion.base.exceptions.NotFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsignaturaService implements IAsignaturaService {
    private final AsignaturaRepository asignaturaRepository;
    private final SemestreRepository semestreRepository;
    private final ModelMapper modelMapper;

    @Override
    public AsignaturaDTO create(CrearAsignaturaRequest request) {
        if (asignaturaRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe una asignatura registrada con el código: " + request.getCodigo());
        }

        Semestre semestre = semestreRepository.findById(request.getSemestreId())
                .orElseThrow(() -> new NotFound("Error: No se encontró el semestre con id: " + request.getSemestreId()));

        Asignatura asignatura = Asignatura.builder()
                .codigo(request.getCodigo())
                .nombre(request.getNombre())
                .cantidadMatriculados(request.getCantidadMatriculados())
                .elementosRequeridos(request.getElementosRequeridos())
                .semestre(semestre)
                .build();

        Asignatura guardada = asignaturaRepository.save(asignatura);
        return mapToDTO(guardada);
    }

    @Override
    public List<AsignaturaDTO> findAll() {
        return asignaturaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AsignaturaDTO findById(Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró la asignatura con id: " + id));
        return mapToDTO(asignatura);
    }

    @Override
    public List<AsignaturaDTO> findBySemestreId(Long semestreId) {
        return asignaturaRepository.findBySemestreId(semestreId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AsignaturaDTO update(Long id, CrearAsignaturaRequest request) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró la asignatura con id: " + id));

        if (!asignatura.getCodigo().equals(request.getCodigo()) && asignaturaRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe otra asignatura con el código: " + request.getCodigo());
        }

        Semestre semestre = semestreRepository.findById(request.getSemestreId())
                .orElseThrow(() -> new NotFound("Error: No se encontró el semestre con id: " + request.getSemestreId()));

        asignatura.setCodigo(request.getCodigo());
        asignatura.setNombre(request.getNombre());
        asignatura.setCantidadMatriculados(request.getCantidadMatriculados());
        asignatura.setElementosRequeridos(request.getElementosRequeridos());
        asignatura.setSemestre(semestre);

        return mapToDTO(asignaturaRepository.save(asignatura));
    }

    @Override
    public void delete(Long id) {
        if (!asignaturaRepository.existsById(id)) {
            throw new NotFound("Error: No se encontró la asignatura con id: " + id);
        }
        asignaturaRepository.deleteById(id);
    }

    private AsignaturaDTO mapToDTO(Asignatura asignatura) {
        AsignaturaDTO dto = modelMapper.map(asignatura, AsignaturaDTO.class);
        dto.setSemestreId(asignatura.getSemestre().getId());
        dto.setSemestreCodigo(asignatura.getSemestre().getCodigo());
        dto.setNumeroSemestre(asignatura.getSemestre().getNumeroSemestre());
        return dto;
    }
}