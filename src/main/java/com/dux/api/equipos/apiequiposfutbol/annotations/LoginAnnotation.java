package com.dux.api.equipos.apiequiposfutbol.annotations;

import com.dux.api.equipos.apiequiposfutbol.dtos.LoginUserDTO;
import com.dux.api.equipos.apiequiposfutbol.responses.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Operation(
        summary = "Login",
        description = "Iniciar sesión con nombre de usuario (username) y contraseña (password)",
        tags = {"Autenticación"},
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Se inicio sesión correctamente",
                        content = @Content(schema = @Schema(implementation = LoginResponse.class),
                                examples = {
                                        @ExampleObject(
                                                name = "Token de acceso",
                                                value = "{\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKFh7Zxk9PjHvzHhJp2eu7\"}"
                                        )
                                })
                ),
                @ApiResponse(
                        responseCode = "401",
                        description = "Username o password incorrectos."
                )
        },
        requestBody =
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Credenciales de acceso",
                content = @Content(
                        schema = @Schema(implementation = LoginUserDTO.class),
                        examples = {
                                @ExampleObject(
                                        name = "Credenciales válidas",
                                        value = "{\"username\":\"test\",\"password\":\"12345\"}"
                                )
                        }
                )
        )
)
public @interface LoginAnnotation {
}
