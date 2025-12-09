package com.grupo3.ProyectoLBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo3.ProyectoLBD.model.EncabezadoFacturaView;

@Repository
public interface EncabezadoFacturaViewRepository extends JpaRepository<EncabezadoFacturaView, Long> {
    List<EncabezadoFacturaView> findByEstadoNot(String estado);
    List<EncabezadoFacturaView> findByCedulaApoderado(Long cedula);
    List<EncabezadoFacturaView> findByIdFactura(Long idFactura);
}
