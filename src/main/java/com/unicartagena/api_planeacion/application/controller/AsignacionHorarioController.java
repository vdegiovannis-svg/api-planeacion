package com.unicartagena.api_planeacion.application.controller;

import com.unicartagena.api_planeacion.application.request.AsignacionHorarioRequestDTO;
import com.unicartagena.api_planeacion.application.service.IAsignacionHorarioService;
import com.unicartagena.api_planeacion.base.util.ResponseDTOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asignaciones-horario")
@RequiredArgsConstructor
public class AsignacionHorarioController {

    private final IAsignacionHorarioService asignacionService;
    private final ResponseDTOService responseDTOService;

    @PostMapping
    public ResponseEntity<?> assign(@Valid @RequestBody AsignacionHorarioRequestDTO request) {
        return responseDTOService.response(asignacionService.assign(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return responseDTOService.response(asignacionService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        asignacionService.delete(id);
        return responseDTOService.response(HttpStatus.NO_CONTENT);
    }
}