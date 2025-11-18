package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.FormacionAcademicaView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormacionAcademicaViewRepository
        extends JpaRepository<FormacionAcademicaView, Long> {

    List<FormacionAcademicaView> findByCedula(Long cedula);

    List<FormacionAcademicaView> findByCedulaAndIdEstado(Long cedula, Integer idEstado);
}