package com.grupo3.ProyectoLBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.ProyectoLBD.model.FideMetodoPagoTb;
import java.util.List;


public interface MetodoPagoRepository extends JpaRepository<FideMetodoPagoTb, Integer> {
    List<FideMetodoPagoTb> findByIdEstado(Integer estado);    
}
