package com.grupo3.ProyectoLBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo3.ProyectoLBD.model.DetalleFacturaView;
import com.grupo3.ProyectoLBD.model.DetalleFacturaViewId;

@Repository
public interface DetalleFacturaViewRepository extends JpaRepository<DetalleFacturaView, DetalleFacturaViewId> {

    List<DetalleFacturaView> findByIdFactura(Integer idFactura);
}
