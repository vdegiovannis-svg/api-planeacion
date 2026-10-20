package com.unicartagena.api_planeacion.application.service.impl;

import com.unicartagena.api_planeacion.application.dtos.AulaDTO;
import com.unicartagena.api_planeacion.application.request.CrearAulaRequest;
import com.unicartagena.api_planeacion.application.service.IAulaService;
import com.unicartagena.api_planeacion.base.exceptions.AlreadyExists;
import com.unicartagena.api_planeacion.base.exceptions.NotFound;
import com.unicartagena.api_planeacion.application.entities.Aula;
import com.unicartagena.api_planeacion.application.repositories.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AulaService implements IAulaService {

    private final AulaRepository aulaRepository;
    private final ModelMapper modelMapper;

    @Override
    public AulaDTO create(CrearAulaRequest request) {
        if (aulaRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe un aula registrada con el código: " + request.getCodigo());
        }

        Aula aula = modelMapper.map(request, Aula.class);
        Aula aulaGuardada = aulaRepository.save(aula);

        return modelMapper.map(aulaGuardada, AulaDTO.class);
    }

    @Override
    public List<AulaDTO> findAll() {
        return aulaRepository.findAll()
                .stream()
                .map(aula -> modelMapper.map(aula, AulaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AulaDTO findById(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró el aula con id: " + id));

        return modelMapper.map(aula, AulaDTO.class);
    }

    @Override
    public AulaDTO update(Long id, CrearAulaRequest request) {
        Aula aulaExistente = aulaRepository.findById(id)
                .orElseThrow(() -> new NotFound("Error: No se encontró el aula para actualizar con id: " + id));

        if (!aulaExistente.getCodigo().equals(request.getCodigo())
                && aulaRepository.existsByCodigo(request.getCodigo())) {
            throw new AlreadyExists("Error: Ya existe otra aula con el código: " + request.getCodigo());
        }

        aulaExistente.setCodigo(request.getCodigo());
        aulaExistente.setNombre(request.getNombre());
        aulaExistente.setTipo(request.getTipo());
        aulaExistente.setUbicacion(request.getUbicacion());
        aulaExistente.setCapacidad(request.getCapacidad());

        Aula aulaActualizada = aulaRepository.save(aulaExistente);

        return modelMapper.map(aulaActualizada, AulaDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!aulaRepository.existsById(id)) {
            throw new NotFound("Error: No se encontró el aula con id: " + id);
        }
        aulaRepository.deleteById(id);
    }
}