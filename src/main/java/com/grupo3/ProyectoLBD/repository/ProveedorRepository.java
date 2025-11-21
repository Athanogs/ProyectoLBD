package com.grupo3.ProyectoLBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.ProyectoLBD.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    
}