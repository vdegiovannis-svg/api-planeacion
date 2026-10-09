package com.unicartagena.api_planeacion.application.service;

import com.unicartagena.api_planeacion.application.dtos.AulaDTO;
import com.unicartagena.api_planeacion.application.request.CrearAulaRequest;

import java.util.List;

public interface IAulaService {
    AulaDTO create(CrearAulaRequest request);
    List<AulaDTO> findAll();
    AulaDTO findById(Long id);
    AulaDTO update(Long id, CrearAulaRequest request);
    void delete(Long id);
}
