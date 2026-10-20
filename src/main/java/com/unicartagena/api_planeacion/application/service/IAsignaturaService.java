package com.unicartagena.api_planeacion.application.service;

import com.unicartagena.api_planeacion.application.dtos.AsignaturaDTO;
import com.unicartagena.api_planeacion.application.request.CrearAsignaturaRequest;

import java.util.List;

public interface IAsignaturaService {
    AsignaturaDTO create(CrearAsignaturaRequest request);
    List<AsignaturaDTO> findAll();
    AsignaturaDTO findById(Long id);
    List<AsignaturaDTO> findBySemestreId(Long semestreId);
    AsignaturaDTO update(Long id, CrearAsignaturaRequest request);
    void delete(Long id);
}
