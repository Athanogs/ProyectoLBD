package com.grupo3.ProyectoLBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo3.ProyectoLBD.model.MatriculasView;

@Repository
public interface MatriculasViewRepository extends JpaRepository<MatriculasView, Integer> {
    List<MatriculasView> findByEstadoMatriculaNot(String estado);

    List<MatriculasView> findByIdMatricula(Integer idMatricula);

    List<MatriculasView> findByInfanteNombreContainingIgnoreCase(String infante);
}
