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

    // ============================================================
    //  FACTURAS POR PADRE
    // ============================================================
    public List<Map<String, Object>> facturasPadre(long cedula) {
        String sql = """
            SELECT 
                ID_FACTURA,
                CEDULA_PADRE,
                NOMBRE_PADRE,
                APE1_PADRE AS APELLIDO_PATERNO_PADRE,
                APE2_PADRE AS APELLIDO_MATERNO_PADRE,

                CEDULA_INFANTE,
                NOMBRE_INFANTE,
                APE1_INFANTE AS APELLIDO_PATERNO_INFANTE,
                APE2_INFANTE AS APELLIDO_MATERNO_INFANTE,

                FECHA_EMISION,
                HORA_EMISION,
                MONTO_TOTAL,
                SUBTOTAL,
                DESCUENTO,
                IVA,

                ID_MATRICULA,
                ID_MENSUALIDAD,
                MES_MENSUALIDAD,

                CASE 
                    WHEN ESTADO_MENSUALIDAD = 1 THEN 'ACTIVO'
                    WHEN ESTADO_MENSUALIDAD = 2 THEN 'INACTIVO'
                    WHEN ESTADO_MENSUALIDAD = 3 THEN 'EMITIDO'
                    WHEN ESTADO_MENSUALIDAD = 4 THEN 'ANULADO'
                    WHEN ESTADO_MENSUALIDAD = 5 THEN 'PAGADO'
                    ELSE 'DESCONOCIDO'
                END AS ESTADO_MENSUALIDAD

            FROM FIDE_REPORTE_FACTURAS_PADRE_V
            WHERE CEDULA_PADRE = ?
        """;

        return jdbc.queryForList(sql, cedula);
    }

    // ============================================================
    //  ASISTENCIA POR RANGO
    // ============================================================
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

                CASE
                    WHEN ID_ESTADO = 1 THEN 'PRESENTE'
                    WHEN ID_ESTADO = 2 THEN 'AUSENTE'
                    WHEN ID_ESTADO = 3 THEN 'JUSTIFICADO'
                    ELSE 'DESCONOCIDO'
                END AS ESTADO_ASISTENCIA

            FROM FIDE_REPORTE_ASISTENCIA_V
            WHERE FECHA BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')
        """;

        return jdbc.queryForList(sql, inicio, fin);
    }

    // ============================================================
    //  ACTIVIDADES POR INFANTE
    // ============================================================
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
                APE1_INFANTE AS APELLIDO_PATERNO_INFANTE,
                APE2_INFANTE AS APELLIDO_MATERNO_INFANTE,
                CALIFICACION,
                OBS_CALIFICACION AS OBSERVACIONES_ACTIVIDAD
            FROM FIDE_REPORTE_ACTIVIDADES_V
            WHERE CEDULA_INFANTE = ?
        """;

        return jdbc.queryForList(sql, cedula);
    }

    // ============================================================
    //  RESUMEN GENERAL POR PADRE
    // ============================================================
    public List<Map<String, Object>> resumenPadre(long cedulaInfante) {
    String sql = """
        SELECT
            CEDULA_INFANTE,
            NOMBRE_INFANTE,
            APE1_INFANTE AS APELLIDO_PATERNO,
            APE2_INFANTE AS APELLIDO_MATERNO,

            FECHA_ASISTENCIA,
            HORA_ENTRADA,
            HORA_SALIDA,

            CASE
                WHEN ESTADO_ASISTENCIA = 1 THEN 'PRESENTE'
                WHEN ESTADO_ASISTENCIA = 2 THEN 'AUSENTE'
                WHEN ESTADO_ASISTENCIA = 3 THEN 'JUSTIFICADO'
                ELSE 'DESCONOCIDO'
            END AS ESTADO_ASISTENCIA,

            OBS_ASISTENCIA AS OBSERVACIONES_ASISTENCIA,

            ID_FACTURA,
            FECHA_EMISION,
            MONTO_TOTAL,
            SUBTOTAL,
            DESCUENTO,
            IVA,

            ID_ACTIVIDAD,
            ACTIVIDAD AS DESCRIPCION_ACTIVIDAD,
            CALIFICACION AS CALIFICACION_ACTIVIDAD,
            OBS_CALIFICACION AS OBSERVACIONES_ACTIVIDAD

        FROM FIDE_REPORTE_RESUMEN_PADRE_V
        WHERE CEDULA_INFANTE = ?
    """;

    return jdbc.queryForList(sql, cedulaInfante);
}

}
