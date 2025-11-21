package com.grupo3.ProyectoLBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.ProyectoLBD.model.FideServicioTb;
import java.util.List;

public interface ServicioRepository extends JpaRepository<FideServicioTb, Integer> {
    List<FideServicioTb> findByIdEstado(Integer estado);    
}
