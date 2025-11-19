package com.grupo3.ProyectoLBD.service;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CapacitacionService {

    private final SimpleJdbcCall insertCapacitacionCall;
    private final SimpleJdbcCall deleteCapacitacionCall;

    public CapacitacionService(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // SP que crea la capacitación y tema (el que ya teníamos)
        this.insertCapacitacionCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_CAPACITACION_INSERT_AUTO_SP");

        // SP que hace el "borrado lógico" (ID_ESTADO = 2)
        this.deleteCapacitacionCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_CAPACITACION_DELETE_SP");
    }

    @Transactional
    public void agregarCapacitacionDocente(Long cedula,
                                           String descripcionTema,
                                           Long idProveedor,
                                           Integer anio,
                                           Integer horas) {

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_CEDULA",           cedula)
                .addValue("P_DESCRIPCION_TEMA", descripcionTema)
                .addValue("P_ID_PROVEEDOR",     idProveedor)
                .addValue("P_ANIO",             anio)
                .addValue("P_HORAS",            horas);

        insertCapacitacionCall.execute(in);
    }

    @Transactional
    public void eliminarCapacitacionDocente(Long cedula,
                                            Integer idTema,
                                            Integer idProveedor) {

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_CEDULA",       cedula)
                .addValue("P_ID_TEMA",      idTema)
                .addValue("P_ID_PROVEEDOR", idProveedor);

        deleteCapacitacionCall.execute(in);
    }
}