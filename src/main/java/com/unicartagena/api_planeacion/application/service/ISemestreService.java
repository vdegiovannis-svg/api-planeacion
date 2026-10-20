package com.unicartagena.api_planeacion.application.service;

import com.unicartagena.api_planeacion.application.dtos.SemestreDTO;
import com.unicartagena.api_planeacion.application.request.CrearSemestreRequest;

import java.util.List;

public interface ISemestreService {
    SemestreDTO create(CrearSemestreRequest request);
    List<SemestreDTO> findAll();
    SemestreDTO findById(Long id);
    List<SemestreDTO> findByProgramaId(Long programaId);
    SemestreDTO update(Long id, CrearSemestreRequest request);
    void delete(Long id);
}
