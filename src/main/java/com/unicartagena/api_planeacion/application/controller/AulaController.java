package com.unicartagena.api_planeacion.application.controller;

import com.unicartagena.api_planeacion.application.dtos.AulaDTO;
import com.unicartagena.api_planeacion.application.request.CrearAulaRequest;
import com.unicartagena.api_planeacion.application.service.IAulaService;
import com.unicartagena.api_planeacion.base.util.ResponseDTOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final IAulaService iAulaService;
    private final ResponseDTOService responseDTOService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CrearAulaRequest request) {
        AulaDTO aulaGuardada = iAulaService.create(request);
        return responseDTOService.response(aulaGuardada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AulaDTO> aulas = iAulaService.findAll();
        return responseDTOService.response(aulas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        AulaDTO aula = iAulaService.findById(id);
        return responseDTOService.response(aula, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CrearAulaRequest request) {
        AulaDTO aulaActualizada = iAulaService.update(id, request);
        return responseDTOService.response(aulaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        iAulaService.delete(id);
        return responseDTOService.response(HttpStatus.NO_CONTENT);
    }
}