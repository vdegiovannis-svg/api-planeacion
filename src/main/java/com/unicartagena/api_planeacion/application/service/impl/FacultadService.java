package com.unicartagena.api_planeacion.application.service.impl;

import com.unicartagena.api_planeacion.application.dtos.FacultadDTO;
import com.unicartagena.api_planeacion.application.request.CrearFacultadRequest;
import com.unicartagena.api_planeacion.application.service.IFacultadService;
import com.unicartagena.api_planeacion.base.exceptions.AlreadyExists;
import com.unicartagena.api_planeacion.base.exceptions.NotFound;
import com.unicartagena.api_planeacion.application.entities.Facultad;
import com.unicartagena.api_planeacion.application.repositories.FacultadRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultadService implements IFacultadService {

    private final FacultadRepository facultadRepository;
    private final ModelMapper modelMapper;

    @Override
    public FacultadDTO create(CrearFacultadRequest request) {
        if (facultadRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe una facultad con el código: " + request.getCodigo());
        }
        Facultad facultad = modelMapper.map(request, Facultad.class);
        return modelMapper.map(facultadRepository.save(facultad), FacultadDTO.class);
    }

    @Override
    public List<FacultadDTO> findAll() {
        return facultadRepository.findAll().stream()
                .map(f -> modelMapper.map(f, FacultadDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FacultadDTO findById(Long id) {
        Facultad facultad = facultadRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró la facultad con id: " + id));
        return modelMapper.map(facultad, FacultadDTO.class);
    }

    @Override
    public FacultadDTO update(Long id, CrearFacultadRequest request) {
        Facultad facultad = facultadRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró la facultad con id: " + id));

        if (!facultad.getCodigo().equals(request.getCodigo()) && facultadRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe otra facultad con el código: " + request.getCodigo());
        }

        facultad.setCodigo(request.getCodigo());
        facultad.setNombre(request.getNombre());
        facultad.setDescripcion(request.getDescripcion());

        return modelMapper.map(facultadRepository.save(facultad), FacultadDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!facultadRepository.existsById(id)) {
            throw new NotFound("Error: No se encontró la facultad con id: " + id);
        }
        facultadRepository.deleteById(id);
    }
}