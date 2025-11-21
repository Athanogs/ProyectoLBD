package com.grupo3.ProyectoLBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo3.ProyectoLBD.model.PagosView;
import com.grupo3.ProyectoLBD.model.PagosViewId;

public interface PagosViewRepository extends JpaRepository<PagosView, PagosViewId> {
    List<PagosView> findByCedulaApoderado(Long cedula);
    List<PagosView> findByEstadoNot(String estado);
    List<PagosView> findByIdPago(Integer idPago);
    
}
