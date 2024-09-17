package com.dux.api.equipos.apiequiposfutbol.service.impl;

import com.dux.api.equipos.apiequiposfutbol.controller.advice.InvalidRequestErrorException;
import com.dux.api.equipos.apiequiposfutbol.entity.Equipo;
import com.dux.api.equipos.apiequiposfutbol.repository.IEquiposRepository;
import com.dux.api.equipos.apiequiposfutbol.service.IEquipoService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EquipoService implements IEquipoService {

    private IEquiposRepository equiposRepository;

    @Override
    public List<Equipo> findAll() {
        return equiposRepository.findAll();
    }

    @Override
    public Equipo findEquipoById(Long id) {
        Optional<Equipo> equipo = equiposRepository.findById(id);
        if (!equipo.isPresent()) {
            throw new IllegalArgumentException("Equipo no encontrado");
        }
        return equipo.get();
    }

    @Override
    public  List<Equipo> findEquipoByNombre(String nombre) {
        List<Equipo> equipos = equiposRepository.findEquipoByNombre(nombre);
        return equipos;
    }

    @Override
    public Equipo updateEquipo(Equipo equipo, Long id) {
        Optional<Equipo> equipoAEditar =  equiposRepository.findById(id);
        if (!equipoAEditar.isPresent()) {
            throw new IllegalArgumentException("Equipo no encontrado");
        }
        validarEquipo(equipo);
        equipoAEditar.get().setNombre(equipo.getNombre());
        equipoAEditar.get().setLiga(equipo.getLiga());
        equipoAEditar.get().setPais(equipo.getPais());
        return equiposRepository.save(equipoAEditar.get());
    }

    @Override
    public Equipo saveEquipo(Equipo equipo) {
        validarEquipo(equipo);
        try{
            return equiposRepository.save(equipo);
        }catch (ConstraintViolationException exception){
            throw  exception;
        }
    }

    @Override
    public void deleteEquipoById(Long id) {
        try{
            Optional<Equipo> equipo = equiposRepository.findById(id);
            if (!equipo.isPresent()) {
                throw new InvalidRequestErrorException("Equipo no encontrado", 404);
            }
            equiposRepository.deleteById(id);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    private void validarEquipo(Equipo equipo) {
        try{
            if ((equipo.getNombre() == null || equipo.getNombre().isEmpty()) ||
                    (equipo.getLiga() == null || equipo.getLiga().isEmpty()) ||
                    (equipo.getPais() == null || equipo.getPais().isEmpty())) {
                throw new InvalidRequestErrorException("La solicitud es invalida", 400);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
