package com.grupo3.ProyectoLBD.service;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.grupo3.ProyectoLBD.model.DetalleFacturaView;
import com.grupo3.ProyectoLBD.model.EncabezadoFacturaView;
import com.grupo3.ProyectoLBD.model.FideMetodoPagoTb;
import com.grupo3.ProyectoLBD.model.FideServicioTb;
import com.grupo3.ProyectoLBD.repository.DetalleFacturaViewRepository;
import com.grupo3.ProyectoLBD.repository.EncabezadoFacturaViewRepository;
import com.grupo3.ProyectoLBD.repository.MetodoPagoRepository;
import com.grupo3.ProyectoLBD.repository.ServicioRepository;

@Service
public class FacturacionService {

    private final EncabezadoFacturaViewRepository encabezadoFacturaRepo;
    private final DetalleFacturaViewRepository detalleFacturaRepo;
    private final MetodoPagoRepository metodoPagoRepo;
    private final ServicioRepository servicioRepo;
    private final JdbcTemplate jdbcTemplate;

    public FacturacionService(
            EncabezadoFacturaViewRepository encabezadoFacturaRepo,
            DetalleFacturaViewRepository detalleFacturaRepo,
            MetodoPagoRepository metodoPagoRepo,
            ServicioRepository servicioRepo,
            JdbcTemplate jdbcTemplate
    ) {
        this.encabezadoFacturaRepo = encabezadoFacturaRepo;
        this.detalleFacturaRepo = detalleFacturaRepo;
        this.metodoPagoRepo = metodoPagoRepo;
        this.servicioRepo = servicioRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FideMetodoPagoTb> obtenerMetodosPagoActivos() {
        return metodoPagoRepo.findByIdEstado(1);
    }

    public List<FideServicioTb> obtenerServiciosActivos() {
        return servicioRepo.findByIdEstado(1);
    }

    public EncabezadoFacturaView obtenerEncabezado(Long idFactura) {
        List<EncabezadoFacturaView> lista = encabezadoFacturaRepo.findByIdFactura(idFactura);

        if (lista == null || lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }

    public List<DetalleFacturaView> obtenerDetalle(Long idFactura) {
        return detalleFacturaRepo.findByIdFactura(idFactura);
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

    public Long insertarEncabezadoFactura(
            Long cedula,
            Long cedulaInfante,
            LocalDate fechaEmision,
            String horaEmision,
            BigDecimal subtotal,
            BigDecimal descuento,
            BigDecimal iva,
            BigDecimal total,
            Integer idMetodoPago
    ) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_ENCABEZADO_FACTURA_INSERT_SP")
                .declareParameters(new SqlOutParameter("O_ID_FACTURA", Types.NUMERIC));

        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", cedula);
        params.put("P_CEDULA_INFANTE", cedulaInfante);
        params.put("P_FECHA_EMISION", fechaEmision);
        params.put("P_HORA_EMISION", horaEmision);
        params.put("P_SUBTOTAL", subtotal);
        params.put("P_DESCUENTO", descuento);
        params.put("P_IVA", iva);
        params.put("P_MONTO_TOTAL", total);
        params.put("P_ID_METODO_PAGO", idMetodoPago);
        params.put("P_ID_ESTADO", 3);

        Map<String, Object> result = call.execute(params);
        BigDecimal idFactura = (BigDecimal) result.get("O_ID_FACTURA");

        return idFactura != null ? idFactura.longValue() : null;
    }

    public void insertarDetalleFactura(
            Long idFactura,
            Integer idServicio,
            BigDecimal montoPagado
    ) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_DETALLE_FACTURA_INSERT_SP");

        Map<String, Object> params = new HashMap<>();

        params.put("P_ID_FACTURA", idFactura);
        params.put("P_ID_SERVICIO", idServicio);
        params.put("P_MONTO_PAGADO", montoPagado);
        params.put("P_ID_ESTADO", 3);

        call.execute(params);
    }

    public void eliminarEncabezadoFactura(Long idFactura) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_ENCABEZADO_FACTURA_DELETE_SP");

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_FACTURA", idFactura);
        call.execute(params);
    }

    public void eliminarDetalleFactura(Long idFactura, Integer idServicio) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_DETALLE_FACTURA_DELETE_SP");

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_FACTURA", idFactura);
        params.put("P_ID_SERVICIO", idServicio);
        call.execute(params);
    }

}
