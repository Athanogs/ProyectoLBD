package com.grupo3.ProyectoLBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.ProyectoLBD.model.FideUsuarioTb;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<FideUsuarioTb, Long> {

    // 🔵 NECESARIO PARA SPRING SECURITY
    Optional<FideUsuarioTb> findByUsername(String username);
}