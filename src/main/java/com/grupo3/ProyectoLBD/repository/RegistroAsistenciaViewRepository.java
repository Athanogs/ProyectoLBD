package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.RegistroAsistenciaView;
import com.grupo3.ProyectoLBD.model.RegistroAsistenciaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface RegistroAsistenciaViewRepository
        extends JpaRepository<RegistroAsistenciaView, RegistroAsistenciaId> {

    List<RegistroAsistenciaView> findByCedulaInfante(Long cedula);

    @Query("SELECT r FROM RegistroAsistenciaView r " +
           "WHERE LOWER(r.nombreInfante) LIKE LOWER(CONCAT('%', :valor, '%')) " +
           "OR LOWER(r.apellidoPaterno) LIKE LOWER(CONCAT('%', :valor, '%')) " +
           "OR LOWER(r.apellidoMaterno) LIKE LOWER(CONCAT('%', :valor, '%'))"+
           "OR TO_CHAR(r.cedulaInfante) LIKE CONCAT('%', :valor, '%')")
    //List<RegistroAsistenciaView> buscarPorNombreApellido(@Param("valor") String valor);
    List<RegistroAsistenciaView> buscarPorNombreApellidoOCedula(@Param("valor") String valor);

    List<RegistroAsistenciaView> findByFechaAsistenciaBetween(Date inicio, Date fin);
}