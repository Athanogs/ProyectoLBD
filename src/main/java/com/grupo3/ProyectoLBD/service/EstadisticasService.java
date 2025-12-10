package com.grupo3.ProyectoLBD.service;

import java.math.BigDecimal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasService {
    private final JdbcTemplate jdbcTemplate;

    public EstadisticasService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer totalAdministradores() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_CONTAR_TOTAL_ADMINISTRADORES_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class);
        return valor.intValue();
    }

    public Integer totalDocentes() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_CONTAR_TOTAL_DOCENTES_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class);
        return valor.intValue();
    }

    public Integer totalApoderados() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_CONTAR_TOTAL_APODERADOS_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class);
        return valor.intValue();
    }
    
    public Integer totalInfantes() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_CONTAR_TOTAL_INFANTES_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class);
        return valor.intValue();
    }

    public Integer totalGrupos() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_CONTAR_TOTAL_GRUPOS_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class);
        return valor.intValue();
    }

    public Integer totalAsistencias() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_TOTAL_ASISTENCIA_HOY_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class);
        return valor.intValue();
    }

    public Integer totalFacturas() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_TOTAL_FACTURAS_HOY_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class);
        return valor.intValue();
    }
}
