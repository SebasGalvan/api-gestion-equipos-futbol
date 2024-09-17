package com.dux.api.equipos.apiequiposfutbol.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CustomResponse {

    private String mensaje;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detalle;
    private Integer codigo;


    public CustomResponse(String mensaje, Integer codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public CustomResponse(String mensaje, String detalle, Integer codigo) {
        this.mensaje = mensaje;
        this.detalle = detalle;
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                (mensaje != null ? ", mensaje='" + mensaje + '\'' : "") +
                (detalle != null ? ", detalle='" + detalle + '\'' : "") +
                (codigo != null ? ", codigo='" + codigo + '\'' : "") +
                '}';
    }
}
