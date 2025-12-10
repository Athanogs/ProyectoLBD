package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.ActividadGrupo;
import com.grupo3.ProyectoLBD.model.ActividadGrupoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActividadGrupoViewRepository 
       extends JpaRepository<ActividadGrupoView, ActividadGrupo>{

    // Buscar por ID de actividad
    List<ActividadGrupoView> findByIdActividad(Integer idActividad);

    // Buscar por descripción de la actividad
    List<ActividadGrupoView> findByDescripcionContainingIgnoreCase(String descripcion);

    // Buscar actividades por ID del grupo
    List<ActividadGrupoView> findByIdGrupo(Integer idGrupo);

    // Buscar por nombre del grupo
    List<ActividadGrupoView> findByNombreGrupoContainingIgnoreCase(String nombreGrupo);
}