package com.grupo3.ProyectoLBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo3.ProyectoLBD.model.MensualidadesView;

@Repository
public interface MensualidadesViewRepository extends JpaRepository<MensualidadesView, Integer> {
    List<MensualidadesView> findByEstadoMensualidadNot(String estado);

    List<MensualidadesView> findByIdMensualidad(Integer idMensualidad);

    List<MensualidadesView> findByInfanteNombreContainingIgnoreCase(String infante);
}
