package com.unicartagena.api_planeacion.application.controller;

import com.unicartagena.api_planeacion.application.dtos.FacultadDTO;
import com.unicartagena.api_planeacion.application.request.CrearFacultadRequest;
import com.unicartagena.api_planeacion.application.service.IFacultadService;
import com.unicartagena.api_planeacion.base.util.ResponseDTOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facultades")
@RequiredArgsConstructor
public class FacultadController {

    private final IFacultadService facultadService;
    private final ResponseDTOService responseDTOService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CrearFacultadRequest request) {
        FacultadDTO dto = facultadService.create(request);
        return responseDTOService.response(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return responseDTOService.response(facultadService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return responseDTOService.response(facultadService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CrearFacultadRequest request) {
        return responseDTOService.response(facultadService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        facultadService.delete(id);
        return responseDTOService.response(HttpStatus.NO_CONTENT);
    }
}