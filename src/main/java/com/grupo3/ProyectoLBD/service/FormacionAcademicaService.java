package com.grupo3.ProyectoLBD.service;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormacionAcademicaService {

    private final SimpleJdbcCall insertFormacionCall;
    private final SimpleJdbcCall deleteFormacionCall; 

    public FormacionAcademicaService(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertFormacionCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_FORMACION_ACADEMICA_TB_INSERT_AUTO_SP");

        this.deleteFormacionCall = new SimpleJdbcCall(jdbcTemplate)  
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_FORMACION_ACADEMICA_TB_DELETE_SP");
    }

    @Transactional
    public void agregarFormacionDocente(Long cedula,
                                        Long idInstitucion,
                                        String titulo,
                                        LocalDate fechaObtencion) {
        Integer idEstado = 1; // ACTIVO

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_CEDULA", cedula)
                .addValue("P_ID_INSTITUCION", idInstitucion)
                .addValue("P_TITULO", titulo)
                .addValue("P_FECHA_OBTENCION", java.sql.Date.valueOf(fechaObtencion))
                .addValue("P_ID_ESTADO", idEstado);

        insertFormacionCall.execute(in);
    }

    // NUEVO: usar el SP FIDE_FORMACION_ACADEMICA_TB_DELETE_SP
    @Transactional
    public void eliminarFormacionDocente(Long cedula, Long idFormacion) {

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_ID_FORMACION", idFormacion)
                .addValue("P_CEDULA", cedula);

        deleteFormacionCall.execute(in);
    }
}