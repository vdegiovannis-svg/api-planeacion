package com.unicartagena.api_planeacion.application.service;

import com.unicartagena.api_planeacion.application.dtos.AsignacionHorarioDTO;
import com.unicartagena.api_planeacion.application.request.AsignacionHorarioRequestDTO;

import java.util.List;

public interface IAsignacionHorarioService {
    AsignacionHorarioDTO assign(AsignacionHorarioRequestDTO request);
    List<AsignacionHorarioDTO> findAll();
    void delete(Long id);
}