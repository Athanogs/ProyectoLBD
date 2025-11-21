package com.grupo3.ProyectoLBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo3.ProyectoLBD.model.PersonaUsuarioRolView;

@Repository
public interface PersonaUsuarioRolViewRepository extends JpaRepository<PersonaUsuarioRolView, Long> {

    List<PersonaUsuarioRolView> findByCedula(Long cedula);

    @Query("SELECT p FROM PersonaUsuarioRolView p WHERE p.idEstado = 1")
    List<PersonaUsuarioRolView> findbyIdEstado();

    @Query("SELECT p FROM PersonaUsuarioRolView p WHERE p.idRol = 3")
    List<PersonaUsuarioRolView> findbyIdRol();

    List<PersonaUsuarioRolView> findByNombreContainingIgnoreCaseOrApellidoPaternoContainingIgnoreCaseOrApellidoMaternoContainingIgnoreCase(
            String nombre, String apellidoPaterno, String apellidoMaterno);
}