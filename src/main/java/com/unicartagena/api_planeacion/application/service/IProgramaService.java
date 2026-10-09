package com.unicartagena.api_planeacion.application.service;

import com.unicartagena.api_planeacion.application.dtos.ProgramaDTO;
import com.unicartagena.api_planeacion.application.request.CrearProgramaRequest;

import java.util.List;

public interface IProgramaService {
    ProgramaDTO create(CrearProgramaRequest request);
    List<ProgramaDTO> findAll();
    ProgramaDTO findById(Long id);
    List<ProgramaDTO> findByFacultadId(Long facultadId);
    ProgramaDTO update(Long id, CrearProgramaRequest request);
    void delete(Long id);
}