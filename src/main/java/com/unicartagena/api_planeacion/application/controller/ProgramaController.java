package com.unicartagena.api_planeacion.application.controller;

import com.unicartagena.api_planeacion.application.request.CrearProgramaRequest;
import com.unicartagena.api_planeacion.application.service.IProgramaService;
import com.unicartagena.api_planeacion.base.util.ResponseDTOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/programas")
@RequiredArgsConstructor
public class ProgramaController {

    private final IProgramaService programaService;
    private final ResponseDTOService responseDTOService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CrearProgramaRequest request) {
        return responseDTOService.response(programaService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return responseDTOService.response(programaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return responseDTOService.response(programaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/facultad/{facultadId}")
    public ResponseEntity<?> findByFacultadId(@PathVariable Long facultadId) {
        return responseDTOService.response(programaService.findByFacultadId(facultadId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CrearProgramaRequest request) {
        return responseDTOService.response(programaService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        programaService.delete(id);
        return responseDTOService.response(HttpStatus.NO_CONTENT);
    }
}