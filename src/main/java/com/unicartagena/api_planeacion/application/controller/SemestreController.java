package com.unicartagena.api_planeacion.application.controller;

import com.unicartagena.api_planeacion.application.request.CrearSemestreRequest;
import com.unicartagena.api_planeacion.application.service.ISemestreService;
import com.unicartagena.api_planeacion.base.util.ResponseDTOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semestres")
@RequiredArgsConstructor
public class SemestreController {

    private final ISemestreService semestreService;
    private final ResponseDTOService responseDTOService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CrearSemestreRequest request) {
        return responseDTOService.response(semestreService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return responseDTOService.response(semestreService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return responseDTOService.response(semestreService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/programa/{programaId}")
    public ResponseEntity<?> findByProgramaId(@PathVariable Long programaId) {
        return responseDTOService.response(semestreService.findByProgramaId(programaId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CrearSemestreRequest request) {
        return responseDTOService.response(semestreService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        semestreService.delete(id);
        return responseDTOService.response(HttpStatus.NO_CONTENT);
    }
}