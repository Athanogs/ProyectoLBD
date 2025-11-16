package com.grupo3.ProyectoLBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.ProyectoLBD.model.FideUsuarioTb;

public interface UsuarioRepository extends JpaRepository<FideUsuarioTb, Long> {}