package com.grupo3.ProyectoLBD.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class AuditoriaRepository {

    private final JdbcTemplate jdbc;

    public AuditoriaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // ============================================================
    //  AUDITORÍA GENERAL (Todas las tablas)
    // ============================================================
    public List<Map<String, Object>> auditoriaGeneral() {
        String sql = """
            SELECT 
                ID_AUDITORIA,
                NOMBRE_TABLA,
                ACCION,
                TO_CHAR(FECHA_ACCION, 'DD/MM/YYYY') AS FECHA,
                TO_CHAR(FECHA_ACCION, 'HH24:MI:SS') AS HORA,
                USUARIO
            FROM FIDE_AUDITORIA_TB
            ORDER BY ID_AUDITORIA DESC
        """;

        return jdbc.queryForList(sql);
    }

    // ============================================================
    //  AUDITORÍA POR TABLA ESPECÍFICA
    // ============================================================
    public List<Map<String, Object>> auditoriaPorTabla(String nombreTabla) {
        String sql = """
            SELECT 
                ID_AUDITORIA,
                NOMBRE_TABLA,
                ACCION,
                TO_CHAR(FECHA_ACCION, 'DD/MM/YYYY') AS FECHA,
                TO_CHAR(FECHA_ACCION, 'HH24:MI:SS') AS HORA,
                USUARIO
            FROM FIDE_AUDITORIA_TB
            WHERE NOMBRE_TABLA = ?
            ORDER BY ID_AUDITORIA DESC
        """;

        return jdbc.queryForList(sql, nombreTabla);
    }

    // ============================================================
    //  AUDITORÍA DE PERSONAS
    // ============================================================
    public List<Map<String, Object>> auditoriaPersonas() {
        String sql = """
            SELECT 
                ID_AUDITORIA,
                ACCION,
                TO_CHAR(FECHA_ACCION, 'DD/MM/YYYY') AS FECHA,
                TO_CHAR(FECHA_ACCION, 'HH24:MI:SS') AS HORA,
                USUARIO,
                
                CEDULA,
                NOMBRE,
                APELLIDO_PATERNO,
                APELLIDO_MATERNO,
                
                TO_CHAR(FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
                TO_CHAR(FECHA_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_MODIFICACION,
                CREADO_POR,
                MODIFICADO_POR
                
            FROM FIDE_AUDITORIA_PERSONAS_V
            ORDER BY ID_AUDITORIA DESC
        """;

        return jdbc.queryForList(sql);
    }

    // ============================================================
    //  AUDITORÍA DE INFANTES
    // ============================================================
    public List<Map<String, Object>> auditoriaInfantes() {
        String sql = """
            SELECT 
                ID_AUDITORIA,
                ACCION,
                TO_CHAR(FECHA_ACCION, 'DD/MM/YYYY') AS FECHA,
                TO_CHAR(FECHA_ACCION, 'HH24:MI:SS') AS HORA,
                USUARIO,
                
                CEDULA_INFANTE,
                NOMBRE,
                APELLIDO_PATERNO,
                APELLIDO_MATERNO,
                
                TO_CHAR(FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
                TO_CHAR(FECHA_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_MODIFICACION,
                CREADO_POR,
                MODIFICADO_POR
                
            FROM FIDE_AUDITORIA_INFANTES_V
            ORDER BY ID_AUDITORIA DESC
        """;

        return jdbc.queryForList(sql);
    }

    // ============================================================
    //  AUDITORÍA DE FACTURAS
    // ============================================================
    public List<Map<String, Object>> auditoriaFacturas() {
        String sql = """
            SELECT 
                ID_AUDITORIA,
                ACCION,
                TO_CHAR(FECHA_ACCION, 'DD/MM/YYYY') AS FECHA,
                TO_CHAR(FECHA_ACCION, 'HH24:MI:SS') AS HORA,
                USUARIO,
                
                ID_FACTURA,
                CEDULA,
                CEDULA_INFANTE,
                MONTO_TOTAL,
                
                TO_CHAR(FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
                TO_CHAR(FECHA_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_MODIFICACION,
                CREADO_POR,
                MODIFICADO_POR
                
            FROM FIDE_AUDITORIA_FACTURAS_V
            ORDER BY ID_AUDITORIA DESC
        """;

        return jdbc.queryForList(sql);
    }

    // ============================================================
    //  AUDITORÍA DE MATRÍCULAS
    // ============================================================
    public List<Map<String, Object>> auditoriaMatriculas() {
        String sql = """
            SELECT 
                ID_AUDITORIA,
                ACCION,
                TO_CHAR(FECHA_ACCION, 'DD/MM/YYYY') AS FECHA,
                TO_CHAR(FECHA_ACCION, 'HH24:MI:SS') AS HORA,
                USUARIO,
                
                ID_MATRICULA,
                CEDULA,
                CEDULA_INFANTE,
                TO_CHAR(FECHA_MATRICULA, 'DD/MM/YYYY') AS FECHA_MATRICULA,
                
                TO_CHAR(FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
                TO_CHAR(FECHA_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_MODIFICACION,
                CREADO_POR,
                MODIFICADO_POR
                
            FROM FIDE_AUDITORIA_MATRICULAS_V
            ORDER BY ID_AUDITORIA DESC
        """;

        return jdbc.queryForList(sql);
    }

    // ============================================================
    //  AUDITORÍA DE ACTIVIDADES
    // ============================================================
    public List<Map<String, Object>> auditoriaActividades() {
        String sql = """
            SELECT 
                ID_AUDITORIA,
                ACCION,
                TO_CHAR(FECHA_ACCION, 'DD/MM/YYYY') AS FECHA,
                TO_CHAR(FECHA_ACCION, 'HH24:MI:SS') AS HORA,
                USUARIO,
                
                ID_ACTIVIDAD,
                DESCRIPCION,
                TO_CHAR(FECHA, 'DD/MM/YYYY') AS FECHA_ACTIVIDAD,
                HORA AS HORA_ACTIVIDAD,
                
                TO_CHAR(FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
                TO_CHAR(FECHA_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_MODIFICACION,
                CREADO_POR,
                MODIFICADO_POR
                
            FROM FIDE_AUDITORIA_ACTIVIDADES_V
            ORDER BY ID_AUDITORIA DESC
        """;

        return jdbc.queryForList(sql);
    }

    // ============================================================
    //  AUDITORÍA DE PAGOS
    // ============================================================
    public List<Map<String, Object>> auditoriaPagos() {
        String sql = """
            SELECT 
                ID_AUDITORIA,
                ACCION,
                TO_CHAR(FECHA_ACCION, 'DD/MM/YYYY') AS FECHA,
                TO_CHAR(FECHA_ACCION, 'HH24:MI:SS') AS HORA,
                USUARIO,
                
                ID_PAGO,
                ID_FACTURA,
                MONTO_TOTAL,
                
                TO_CHAR(FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
                TO_CHAR(FECHA_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_MODIFICACION,
                CREADO_POR,
                MODIFICADO_POR
                
            FROM FIDE_AUDITORIA_PAGOS_V
            ORDER BY ID_AUDITORIA DESC
        """;

        return jdbc.queryForList(sql);
    }

    // ============================================================
    //  LISTAR TODAS LAS TABLAS CON AUDITORÍA
    // ============================================================
    public List<Map<String, Object>> listarTablasAuditadas() {
        String sql = """
            SELECT DISTINCT NOMBRE_TABLA 
            FROM FIDE_AUDITORIA_TB 
            ORDER BY NOMBRE_TABLA
        """;

        return jdbc.queryForList(sql);
    }
}