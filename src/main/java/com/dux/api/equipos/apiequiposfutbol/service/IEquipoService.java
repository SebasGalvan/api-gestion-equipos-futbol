package com.dux.api.equipos.apiequiposfutbol.service;

import com.dux.api.equipos.apiequiposfutbol.entity.Equipo;

import java.util.List;

public interface IEquipoService {

    public List<Equipo> findAll();
    public List<Equipo>  findEquipoByNombre(String nombre);
    public Equipo findEquipoById(Long id);
    public Equipo updateEquipo(Equipo equipo, Long id) ;
    public Equipo saveEquipo(Equipo equipo);
    public void deleteEquipoById(Long id);
}
