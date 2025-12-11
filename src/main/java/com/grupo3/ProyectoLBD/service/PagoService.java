package com.grupo3.ProyectoLBD.service;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    private final JdbcTemplate jdbcTemplate;

    public PagoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertarPago(
            Long idFactura,
            BigDecimal montoTotal
    ) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_PAGO_INSERT_SP");

        Map<String, Object> params = new HashMap<>();

        params.put("P_ID_FACTURA", idFactura);
        params.put("P_MONTO_TOTAL", montoTotal);
        params.put("P_ID_ESTADO", 1);

        call.execute(params);
    }

    public void eliminarPago(Integer idPago, Long idFactura) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_PAGO_DELETE_SP")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_ID_PAGO", Types.INTEGER),
                        new SqlParameter("P_ID_FACTURA", Types.NUMERIC)
                );

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PAGO", idPago);
        params.put("P_ID_FACTURA", idFactura);

        call.execute(params);
    }

}
