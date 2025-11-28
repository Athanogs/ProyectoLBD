package com.grupo3.ProyectoLBD.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ReportesRepository {

    private final JdbcTemplate jdbc;

    public ReportesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Map<String, Object>> facturasPadre(long cedula) {
        String sql = """
            SELECT 
                ID_FACTURA,
                CEDULA_PADRE,
                NOMBRE_PADRE,
                APE1_PADRE,
                APE2_PADRE,
                CEDULA_INFANTE,
                NOMBRE_INFANTE,
                APE1_INFANTE,
                APE2_INFANTE,
                FECHA_EMISION,
                HORA_EMISION,
                MONTO_TOTAL,
                SUBTOTAL,
                DESCUENTO,
                IVA,
                ID_MATRICULA,
                ID_MENSUALIDAD
            FROM FIDE_REPORTE_FACTURAS_PADRE_V
            WHERE CEDULA_PADRE = ?
        """;

        return jdbc.queryForList(sql, cedula);
    }

    public List<Map<String, Object>> asistenciaRango(String inicio, String fin) {
        String sql = """
            SELECT
                CEDULA_INFANTE,
                NOMBRE,
                APELLIDO_PATERNO,
                APELLIDO_MATERNO,
                FECHA,
                HORA_ENTRADA,
                HORA_SALIDA,
                OBSERVACIONES,
                ID_ESTADO
            FROM FIDE_REPORTE_ASISTENCIA_V
            WHERE FECHA BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')
        """;

        return jdbc.queryForList(sql, inicio, fin);
    }

    public List<Map<String, Object>> actividadesInfante(long cedula) {
        String sql = """
            SELECT
                ID_ACTIVIDAD,
                ACTIVIDAD,
                FECHA,
                HORA,
                OBSERVACIONES,
                CEDULA_INFANTE,
                NOMBRE_INFANTE,
                APE1_INFANTE,
                APE2_INFANTE,
                CALIFICACION,
                OBS_CALIFICACION
            FROM FIDE_REPORTE_ACTIVIDADES_V
            WHERE CEDULA_INFANTE = ?
        """;

        return jdbc.queryForList(sql, cedula);
    }

    public List<Map<String, Object>> resumenPadre(long cedula) {
        String sql = """
            SELECT
                CEDULA_INFANTE,
                NOMBRE_INFANTE,
                APELLIDO_PATERNO,
                APELLIDO_MATERNO,
                FECHA_ASISTENCIA,
                HORA_ENTRADA,
                HORA_SALIDA,
                ID_FACTURA,
                MONTO_TOTAL,
                ID_ACTIVIDAD,
                CALIFICACION,
                OBSERVACIONES
            FROM FIDE_REPORTE_RESUMEN_PADRE_V
            WHERE CEDULA_INFANTE = ?
        """;

        return jdbc.queryForList(sql, cedula);
    }
}
