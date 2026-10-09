package com.unicartagena.api_planeacion.application.service.impl;

import com.unicartagena.api_planeacion.application.dtos.ProgramaDTO;
import com.unicartagena.api_planeacion.application.request.CrearProgramaRequest;
import com.unicartagena.api_planeacion.application.service.IProgramaService;
import com.unicartagena.api_planeacion.base.exceptions.AlreadyExists;
import com.unicartagena.api_planeacion.base.exceptions.NotFound;
import com.unicartagena.api_planeacion.application.entities.Facultad;
import com.unicartagena.api_planeacion.application.entities.Programa;
import com.unicartagena.api_planeacion.application.repositories.FacultadRepository;
import com.unicartagena.api_planeacion.application.repositories.ProgramaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramaService implements IProgramaService {

    private final ProgramaRepository programaRepository;
    private final FacultadRepository facultadRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProgramaDTO create(CrearProgramaRequest request) {
        if (programaRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe un programa con el código: " + request.getCodigo());
        }

        Facultad facultad = facultadRepository.findById(request.getFacultadId())
                .orElseThrow(() -> new NotFound("Error: Facultad no encontrada con id: " + request.getFacultadId()));

        Programa programa = Programa.builder()
                .codigo(request.getCodigo())
                .nombre(request.getNombre())
                .facultad(facultad)
                .build();

        Programa guardado = programaRepository.save(programa);
        return mapToDTO(guardado);
    }

    @Override
    public List<ProgramaDTO> findAll() {
        return programaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramaDTO findById(Long id) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró el programa con id: " + id));
        return mapToDTO(programa);
    }

    @Override
    public List<ProgramaDTO> findByFacultadId(Long facultadId) {
        return programaRepository.findByFacultadId(facultadId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramaDTO update(Long id, CrearProgramaRequest request) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró el programa con id: " + id));

        if (!programa.getCodigo().equals(request.getCodigo()) && programaRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe otro programa con el código: " + request.getCodigo());
        }

        Facultad facultad = facultadRepository.findById(request.getFacultadId())
                .orElseThrow(() -> new NotFound("Error: Facultad no encontrada con id: " + request.getFacultadId()));

        programa.setCodigo(request.getCodigo());
        programa.setNombre(request.getNombre());
        programa.setFacultad(facultad);

        return mapToDTO(programaRepository.save(programa));
    }

    @Override
    public void delete(Long id) {
        if (!programaRepository.existsById(id)) {
            throw new NotFound("Error: No se encontró el programa con id: " + id);
        }
        programaRepository.deleteById(id);
    }

    private ProgramaDTO mapToDTO(Programa programa) {
        ProgramaDTO dto = modelMapper.map(programa, ProgramaDTO.class);
        dto.setFacultadId(programa.getFacultad().getId());
        dto.setFacultadNombre(programa.getFacultad().getNombre());
        return dto;
    }
}