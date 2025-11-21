package com.grupo3.ProyectoLBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo3.ProyectoLBD.model.CapacitacionView;

public interface CapacitacionViewRepository extends JpaRepository<CapacitacionView, Long> {

    List<CapacitacionView> findByCedulaAndIdEstado(Long cedula, Integer idEstado);
}