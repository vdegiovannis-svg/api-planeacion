package com.unicartagena.api_planeacion.application.service;

import com.unicartagena.api_planeacion.application.dtos.FacultadDTO;
import com.unicartagena.api_planeacion.application.request.CrearFacultadRequest;

import java.util.List;

public interface IFacultadService {
    FacultadDTO create(CrearFacultadRequest request);
    List<FacultadDTO> findAll();
    FacultadDTO findById(Long id);
    FacultadDTO update(Long id, CrearFacultadRequest request);
    void delete(Long id);
}