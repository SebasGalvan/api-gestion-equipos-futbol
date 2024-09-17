package com.dux.api.equipos.apiequiposfutbol.controller.advice;


import com.dux.api.equipos.apiequiposfutbol.responses.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<CustomResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        CustomResponse errorResponse = new CustomResponse(e.getMessage(), 404);
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorResponse.getCodigo()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<CustomResponse> handleConstraintViolationException(ConstraintViolationException e) {
        CustomResponse errorResponse = null;
        if (e.getSQLState().equals("23505")){
            errorResponse = new CustomResponse("Violación de indice de Unicidad ó Clave primaria", "El nombre ingresado ya existe", 500);
        }
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorResponse.getCodigo()));
    }

    @ExceptionHandler(InvalidRequestErrorException.class)
    public ResponseEntity<CustomResponse> handleInvalidRequestException(InvalidRequestErrorException e) {
        CustomResponse errorResponse = new CustomResponse(e.getMessage(), e.getCodigo());
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(e.getCodigo()));
    }
}
