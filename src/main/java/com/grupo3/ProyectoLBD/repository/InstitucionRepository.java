package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitucionRepository extends JpaRepository<Institucion, Long> {
    // Por ahora con findAll() nos basta
}