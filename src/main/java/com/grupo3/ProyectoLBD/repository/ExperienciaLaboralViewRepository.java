package com.grupo3.ProyectoLBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo3.ProyectoLBD.model.ExperienciaLaboralView;
import com.grupo3.ProyectoLBD.model.ExperienciaLaboralViewId;

public interface ExperienciaLaboralViewRepository
        extends JpaRepository<ExperienciaLaboralView, ExperienciaLaboralViewId> {

    List<ExperienciaLaboralView> findByCedulaAndIdEstado(Long cedula, Integer idEstado);
}