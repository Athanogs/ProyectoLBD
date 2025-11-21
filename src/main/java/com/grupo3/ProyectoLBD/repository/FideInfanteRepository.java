package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.FideInfanteTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FideInfanteRepository extends JpaRepository<FideInfanteTb, String> {
}