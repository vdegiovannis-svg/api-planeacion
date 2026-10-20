package com.unicartagena.api_planeacion.application.controller;

import com.unicartagena.api_planeacion.application.request.CrearAsignaturaRequest;
import com.unicartagena.api_planeacion.application.service.IAsignaturaService;
import com.unicartagena.api_planeacion.base.util.ResponseDTOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asignaturas")
@RequiredArgsConstructor
public class AsignaturaController {
    private final IAsignaturaService asignaturaService;
    private final ResponseDTOService responseDTOService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CrearAsignaturaRequest request) {
        return responseDTOService.response(asignaturaService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return responseDTOService.response(asignaturaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return responseDTOService.response(asignaturaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/semestre/{semestreId}")
    public ResponseEntity<?> findBySemestreId(@PathVariable Long semestreId) {
        return responseDTOService.response(asignaturaService.findBySemestreId(semestreId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CrearAsignaturaRequest request) {
        return responseDTOService.response(asignaturaService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        asignaturaService.delete(id);
        return responseDTOService.response(HttpStatus.NO_CONTENT);
    }
}