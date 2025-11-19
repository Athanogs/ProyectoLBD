package com.grupo3.ProyectoLBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo3.ProyectoLBD.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}