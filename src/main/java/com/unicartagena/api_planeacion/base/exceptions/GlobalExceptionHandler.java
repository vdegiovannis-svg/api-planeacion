package com.unicartagena.api_planeacion.base.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<ProblemDetail> handleAlreadyExists(AlreadyExists ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Recurso ya existente");
        problemDetail.setType(URI.create("https://cerete.edu.co/errors/already-exists"));
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ProblemDetail> handleNotFound(NotFound ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Recurso no encontrado");
        problemDetail.setType(URI.create("https://cerete.edu.co/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(ScheduleCollisionException.class)
    public ResponseEntity<ProblemDetail> handleScheduleCollision(ScheduleCollisionException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Conflicto en la Asignación de Horario");
        problemDetail.setType(URI.create("https://cerete.edu.co/errors/schedule-collision"));
        problemDetail.setProperty("code", "SCHEDULE_COLLISION");
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }
}