package com.unicartagena.api_planeacion.application.service.impl;

import com.unicartagena.api_planeacion.application.dtos.SemestreDTO;
import com.unicartagena.api_planeacion.application.entities.Programa;
import com.unicartagena.api_planeacion.application.entities.Semestre;
import com.unicartagena.api_planeacion.application.repositories.ProgramaRepository;
import com.unicartagena.api_planeacion.application.repositories.SemestreRepository;
import com.unicartagena.api_planeacion.application.request.CrearSemestreRequest;
import com.unicartagena.api_planeacion.application.service.ISemestreService;
import com.unicartagena.api_planeacion.base.exceptions.AlreadyExists;
import com.unicartagena.api_planeacion.base.exceptions.NotFound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemestreService implements ISemestreService {
    private final SemestreRepository semestreRepository;
    private final ProgramaRepository programaRepository;
    private final ModelMapper modelMapper;

    @Override
    public SemestreDTO create(CrearSemestreRequest request) {
        if (semestreRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe un semestre con el código: " + request.getCodigo());
        }
        Programa programa = programaRepository.findById(request.getProgramaId())
                .orElseThrow(() -> new NotFound("Error: No se encontró el programa con id: " + request.getProgramaId()));

        Semestre semestre = Semestre.builder()
                .codigo(request.getCodigo())
                .numeroSemestre(request.getNumeroSemestre())
                .programa(programa)
                .build();

        Semestre guardado = semestreRepository.save(semestre);
        return mapToDTO(guardado);
    }

    @Override
    public List<SemestreDTO> findAll() {
        return semestreRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SemestreDTO findById(Long id) {
        Semestre semestre = semestreRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró el semestre con id: " + id));
        return mapToDTO(semestre);
    }

    @Override
    public List<SemestreDTO> findByProgramaId(Long programaId) {
        return semestreRepository.findByProgramaId(programaId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SemestreDTO update(Long id, CrearSemestreRequest request) {
        Semestre semestre = semestreRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró el semestre con id: " + id));

        if (!semestre.getCodigo().equals(request.getCodigo()) && semestreRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe otro semestre con el código: " + request.getCodigo());
        }

        Programa programa = programaRepository.findById(request.getProgramaId())
                .orElseThrow(() -> new NotFound("Error: No se encontró el programa con id: " + request.getProgramaId()));

        semestre.setCodigo(request.getCodigo());
        semestre.setNumeroSemestre(request.getNumeroSemestre());
        semestre.setPrograma(programa);

        return mapToDTO(semestreRepository.save(semestre));
    }

    @Override
    public void delete(Long id) {
        if (!semestreRepository.existsById(id)) {
            throw new NotFound("Error: No se encontró el semestre con id: " + id);
        }
        semestreRepository.deleteById(id);
    }

    private SemestreDTO mapToDTO(Semestre semestre) {
        SemestreDTO dto = modelMapper.map(semestre, SemestreDTO.class);
        dto.setProgramaId(semestre.getPrograma().getId());
        dto.setProgramaNombre(semestre.getPrograma().getNombre());
        return dto;
    }
}