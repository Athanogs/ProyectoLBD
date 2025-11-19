package com.grupo3.ProyectoLBD.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    private final JdbcTemplate jdbcTemplate;

    public MatriculaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Llamar función de validación
    public Integer validarFactura(Integer idFactura) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_VALIDAR_FACTURA_PARA_MATRICULA_FN");

        // Oracle devuelve NUMBER → Spring lo mapea como BigDecimal
        BigDecimal valor = call.executeFunction(BigDecimal.class, idFactura);
        return valor.intValue();
    }

    public Integer validarCedulaApoderado(Long cedula) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_VALIDAR_CEDULA_PERSONA_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class, cedula);
        return valor.intValue();
    }

    public Integer validarCedulaInfante(Long cedula) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_VALIDAR_CEDULA_INFANTE_FN");

        BigDecimal valor = call.executeFunction(BigDecimal.class, cedula);
        return valor.intValue();
    }

    // Llamar a procedimiento de inserción
    public void insertarMatricula(
            Integer idMatricula,
            Long cedula,
            Long cedulaInfante,
            Integer idFactura,
            LocalDate fechaMatricula,
            Integer idEstado
    ) {

        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_MATRICULA_INSERT_SP");

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MATRICULA", idMatricula);
        params.put("P_CEDULA", cedula);
        params.put("P_CEDULA_INFANTE", cedulaInfante);
        params.put("P_ID_FACTURA", idFactura);
        params.put("P_FECHA_MATRICULA", fechaMatricula);
        params.put("P_ID_ESTADO", idEstado);

        call.execute(params);
    }

    // Llamar procedimiento de actualización
    public void actualizarMatricula(
            Integer idMatricula,
            Long cedula,
            Long cedulaInfante,
            Integer idFactura,
            LocalDate fechaMatricula,
            Integer idEstado
    ) {

        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_MATRICULA_UPDATE_SP");

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MATRICULA", idMatricula);
        params.put("P_CEDULA", cedula);
        params.put("P_CEDULA_INFANTE", cedulaInfante);
        params.put("P_ID_FACTURA", idFactura);
        params.put("P_FECHA_MATRICULA", fechaMatricula);
        params.put("P_ID_ESTADO", idEstado);

        call.execute(params);
    }

    // Llamar a procedimiento de eliminación o inactivación 
    public void eliminarMatricula(
            Integer idMatricula,
            Long cedula,
            Long cedulaInfante
    ) {

        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_MATRICULA_DELETE_SP");

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MATRICULA", idMatricula);
        params.put("P_CEDULA", cedula);
        params.put("P_CEDULA_INFANTE", cedulaInfante);

        call.execute(params);
    }

}
