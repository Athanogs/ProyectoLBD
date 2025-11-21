package com.grupo3.ProyectoLBD.service;

import com.grupo3.ProyectoLBD.dto.AsistenciaForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AsistenciaService {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcCall insertAsistenciaCall;
    private final SimpleJdbcCall updateAsistenciaCall;
    private final SimpleJdbcCall deleteAsistenciaCall;

    public AsistenciaService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        String packageName = "FIDE_PROYECTO_LBD_PCK"; // reemplaza con tu package de Oracle

        // Procedimientos
        this.insertAsistenciaCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ASISTENCIA_INSERT_SP");

        this.updateAsistenciaCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ASISTENCIA_UPDATE_SP");

        this.deleteAsistenciaCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ASISTENCIA_DELETE_SP");
    }

    // INSERT
    /*public void crearAsistencia(AsistenciaForm form) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA_INFANTE", form.getCedulaInfante());
        params.put("P_FECHA", form.getFechaAsistencia() != null ? Date.valueOf(form.getFechaAsistencia()) : null);
        params.put("P_HORA_ENTRADA", form.getHoraEntrada());
        params.put("P_HORA_SALIDA", form.getHoraSalida());
        params.put("P_OBSERVACIONES", form.getObservaciones());
        params.put("P_ID_ESTADO", form.getIdEstado() != null ? form.getIdEstado() : 1); // 1 = activo

        insertAsistenciaCall.execute(params);
    }*/
    public void crearAsistencia(AsistenciaForm form) {
    Map<String, Object> params = new HashMap<>();
    params.put("P_CEDULA_INFANTE", form.getCedulaInfante());
    params.put("P_FECHA", form.getFechaAsistencia() != null ? Date.valueOf(form.getFechaAsistencia()) : null);

    params.put("P_HORA_ENTRADA",
            form.getHoraEntrada() != null ? java.sql.Time.valueOf(form.getHoraEntrada() + ":00") : null);

    params.put("P_HORA_SALIDA",
            (form.getHoraSalida() != null && !form.getHoraSalida().isEmpty())
                    ? java.sql.Time.valueOf(form.getHoraSalida() + ":00")
                    : null);

    params.put("P_OBSERVACIONES", form.getObservaciones());
    params.put("P_ID_ESTADO", form.getIdEstado() != null ? form.getIdEstado() : 1);

    insertAsistenciaCall.execute(params);
}

    // UPDATE
    public void actualizarAsistencia(AsistenciaForm form) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA_INFANTE", form.getCedulaInfante());
        params.put("P_FECHA", form.getFechaAsistencia() != null ? Date.valueOf(form.getFechaAsistencia()) : null);
        params.put("P_HORA_ENTRADA", form.getHoraEntrada());
        params.put("P_HORA_SALIDA", form.getHoraSalida());
        params.put("P_OBSERVACIONES", form.getObservaciones());
        params.put("P_ID_ESTADO", form.getIdEstado() != null ? form.getIdEstado() : 1);

        updateAsistenciaCall.execute(params);
    }

    // DELETE (cambiar estado a 2)
    public void eliminarAsistencia(Long cedulaInfante, java.time.LocalDate fechaAsistencia) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA_INFANTE", cedulaInfante);
        params.put("P_FECHA", Date.valueOf(fechaAsistencia));

        deleteAsistenciaCall.execute(params);
    }
}