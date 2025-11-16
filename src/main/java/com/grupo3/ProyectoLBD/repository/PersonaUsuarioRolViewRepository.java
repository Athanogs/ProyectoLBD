package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.PersonaUsuarioRolView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaUsuarioRolViewRepository extends JpaRepository<PersonaUsuarioRolView, Long> {

    List<PersonaUsuarioRolView> findByCedula(Long cedula);

    List<PersonaUsuarioRolView> findByNombreContainingIgnoreCaseOrApellidoPaternoContainingIgnoreCaseOrApellidoMaternoContainingIgnoreCase(
            String nombre, String apellidoPaterno, String apellidoMaterno);
}