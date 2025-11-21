package com.grupo3.ProyectoLBD.service;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExperienciaLaboralService {

    private final SimpleJdbcCall insertExperienciaCall;
    private final SimpleJdbcCall deleteExpCall;   // 👈 NUEVO

    public ExperienciaLaboralService(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // SP para crear experiencia + empresa nueva
        this.insertExperienciaCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_EXPERIENCIA_LABORAL_AUTO_SP");

        // SP para "eliminar" (cambiar ID_ESTADO = 2)
        this.deleteExpCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_EXPERIENCIA_LABORAL_DELETE_SP");
    }

    // ✅ INSERTAR EXPERIENCIA (usa FIDE_EXPERIENCIA_LABORAL_AUTO_SP)
    @Transactional
    public void agregarExperienciaDocente(Long cedula,
                                          String empresaDesc,
                                          Long idCargo,
                                          LocalDate fechaInicio,
                                          LocalDate fechaFinal) {

        Integer idEstado = 1; // ACTIVO

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_CEDULA", cedula)
                .addValue("P_EMPRESA_DESC", empresaDesc)
                .addValue("P_ID_CARGO", idCargo)
                .addValue("P_FECHA_INICIO", java.sql.Date.valueOf(fechaInicio))
                .addValue("P_FECHA_FINAL", java.sql.Date.valueOf(fechaFinal))
                .addValue("P_ID_ESTADO", idEstado);

        insertExperienciaCall.execute(in);
    }

    // ✅ ELIMINAR (LÓGICO) EXPERIENCIA (usa FIDE_EXPERIENCIA_LABORAL_DELETE_SP)
    @Transactional
    public void eliminarExperienciaDocente(Long cedula,
                                           Long idEmpresa,
                                           Long idCargo) {

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_CEDULA", cedula)
                .addValue("P_ID_EMPRESA", idEmpresa)
                .addValue("P_ID_CARGO", idCargo);

        deleteExpCall.execute(in);
    }
}