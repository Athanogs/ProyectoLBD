package com.grupo3.ProyectoLBD.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class MensualidadService {

    private final JdbcTemplate jdbcTemplate;

    public MensualidadService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer validarFactura(Integer idFactura) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withFunctionName("FIDE_VALIDAR_FACTURA_PARA_MENSUALIDAD_FN");

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

    public void insertarMensualidad(
            Long cedula,
            Long cedulaInfante,
            Integer idFactura,
            String mes,
            Integer idEstado
    ) {

        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_MENSUALIDAD_INSERT_SP");

        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", cedula);
        params.put("P_CEDULA_INFANTE", cedulaInfante);
        params.put("P_ID_FACTURA", idFactura);
        params.put("P_MES", mes);
        params.put("P_ID_ESTADO", idEstado);

        call.execute(params);
    }

    public void actualizarMensualidad(
            Integer idMensualidad,
            Long cedula,
            Long cedulaInfante,
            Integer idFactura,
            String mes,
            Integer idEstado
    ) {

        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_MENSUALIDAD_UPDATE_SP");

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MENSUALIDAD", idMensualidad);
        params.put("P_CEDULA", cedula);
        params.put("P_CEDULA_INFANTE", cedulaInfante);
        params.put("P_ID_FACTURA", idFactura);
        params.put("P_MES", mes);
        params.put("P_ID_ESTADO", idEstado);

        call.execute(params);
    }

    public void eliminarMensualidad(
            Integer idMensualidad,
            Long cedula,
            Long cedulaInfante,
            Integer idFactura
    ) {

        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_MENSUALIDAD_DELETE_SP");

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MENSUALIDAD", idMensualidad);
        params.put("P_CEDULA", cedula);
        params.put("P_CEDULA_INFANTE", cedulaInfante);
        params.put("P_ID_FACTURA", idFactura);

        call.execute(params);
    }
}
