package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.EvaluacionDocenteId;
import com.grupo3.ProyectoLBD.model.EvaluacionDocenteView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluacionDocenteViewRepository
        extends JpaRepository<EvaluacionDocenteView, EvaluacionDocenteId> {

    // Busca por el campo "cedula" dentro del EmbeddedId
    List<EvaluacionDocenteView> findByIdCedula(Long cedula);

    List<EvaluacionDocenteView> findByIdCedulaAndIdEstado(Long cedula, Integer idEstado);
}