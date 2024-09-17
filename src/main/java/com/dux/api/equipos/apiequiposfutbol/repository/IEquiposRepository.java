package com.dux.api.equipos.apiequiposfutbol.repository;

import com.dux.api.equipos.apiequiposfutbol.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEquiposRepository extends JpaRepository<Equipo, Long> {

    @Query("SELECT e FROM Equipo e WHERE e.nombre ILIKE %:nombre%")
    List<Equipo> findEquipoByNombre(@Param("nombre") String nombre);
}
