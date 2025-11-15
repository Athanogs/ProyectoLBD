package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    // Aquí luego puedes agregar métodos personalizados, si los ocupas
}