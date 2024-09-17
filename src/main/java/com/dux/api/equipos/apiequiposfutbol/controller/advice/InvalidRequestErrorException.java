package com.dux.api.equipos.apiequiposfutbol.controller.advice;

import lombok.Getter;

@Getter
public class InvalidRequestErrorException extends Exception {

    private final int codigo;

    public InvalidRequestErrorException(String mensaje) {
        super(mensaje);
        this.codigo = 400;
    }

    public InvalidRequestErrorException(String mensaje, int codigo) {
        super(mensaje);
        this.codigo = codigo;
    }

}
