package com.unicartagena.api_planeacion.base.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ScheduleCollisionException extends RuntimeException {
    public ScheduleCollisionException(String message) {
        super(message);
    }
}