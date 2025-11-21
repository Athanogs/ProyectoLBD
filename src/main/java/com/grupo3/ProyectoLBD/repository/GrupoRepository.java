package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.FideGrupoTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<FideGrupoTb, Integer> {
}