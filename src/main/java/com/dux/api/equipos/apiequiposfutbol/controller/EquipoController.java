package com.dux.api.equipos.apiequiposfutbol.controller;

import com.dux.api.equipos.apiequiposfutbol.entity.Equipo;
import com.dux.api.equipos.apiequiposfutbol.service.IEquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
@AllArgsConstructor
@Tag(
        name = "⚽ Equipos",
        description = "Endpoints para gestionar equipos."
)
public class EquipoController {

    private IEquipoService equipoService;


    /**
     * Obtiene la lista completa de equipos.
     *
     * @return Lista de equipos.
     */
    @Operation(summary = "Obtiene todos los equipos")
    @GetMapping()
    public List<Equipo> findAll() {
        return equipoService.findAll();
    }

    /**
     * Obtiene un equipo por su ID.
     *
     * @param id ID del equipo.
     * @return Equipo encontrado.
     */
    @Operation(summary = "Obtiene un equipo por ID")
    @Parameter(name = "id", description= "ID del equipo", required = true)
    @GetMapping("{id}")
    public Equipo findEquipoById(@PathVariable Long id) {
        return equipoService.findEquipoById(id);
    }

    /**
     * Busca equipos por nombre.
     *
     * @param nombre Nombre del equipo.
     * @return Lista de equipos que coinciden con el nombre.
     */
    @Operation(summary = "Busca equipos por nombre")
    @Parameter(name = "nombre", description= "Nombre del equipo", required = true)
    @GetMapping("/buscar")
    public  List<Equipo>  findEquipoByName(@RequestParam("nombre") String nombre) {
        return equipoService.findEquipoByNombre(nombre);
    }

    /**
     * Crea un nuevo equipo.
     *
     * @return Equipo creado con estado HTTP 201.
     */
    @Operation(summary = "Crea un nuevo equipo")
    @PostMapping
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo)  {
        Equipo equipoGuardado = equipoService.saveEquipo(equipo);
        return new ResponseEntity<>(equipoGuardado, HttpStatus.CREATED);
    }

    /**
     * Actualiza un equipo existente.
     *
     * @param id ID del equipo a actualizar.
     * @param equipo Datos del equipo actualizado.
     * @return Equipo actualizado.
     */
    @Operation(
            summary = "Actualiza un equipo",
            description = "Actualiza un equipo existente con los datos proporcionados."
    )
    @Parameter(
            name = "id",
            description = "ID del equipo a actualizar",
            required = true,
            example = "1"
    )
    @PutMapping("/{id}")
    public Equipo updateEquipo(@PathVariable Long id,
                               @RequestBody Equipo equipo)  {
        return equipoService.updateEquipo(equipo, id);
    }

    /**
     * Elimina un equipo por su ID.
     *
     * @param id ID del equipo.
     * @return Estado HTTP 204 - No content si se elimina correctamente.
     */
    @Operation(summary = "Elimina un equipo")
    @Parameter(name = "id", description = "ID del equipo", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEquipo(@PathVariable Long id) {
        equipoService.deleteEquipoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
