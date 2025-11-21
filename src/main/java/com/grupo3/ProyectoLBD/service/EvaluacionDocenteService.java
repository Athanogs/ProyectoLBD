package com.grupo3.ProyectoLBD.service;

import com.grupo3.ProyectoLBD.model.EvaluacionDocenteView;
import com.grupo3.ProyectoLBD.repository.EvaluacionDocenteViewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class EvaluacionDocenteService {

    private final EvaluacionDocenteViewRepository evaluacionRepo;
    private final SimpleJdbcCall insertEvaluacionCall;
    private final SimpleJdbcCall deleteEvaluacionCall;

    public EvaluacionDocenteService(EvaluacionDocenteViewRepository evaluacionRepo,
                                    DataSource dataSource) {
        this.evaluacionRepo = evaluacionRepo;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // SP INSERT
        this.insertEvaluacionCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_EVALUACION_DOCENTE_INSERT_SP");

        // SP DELETE (UPDATE ID_ESTADO = 2)
        this.deleteEvaluacionCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_EVALUACION_DOCENTE_DELETE_SP");
    }

    // 📌 Listar evaluaciones desde la vista
    public List<EvaluacionDocenteView> obtenerEvaluacionesPorDocente(Long cedula) {
        return evaluacionRepo.findByIdCedulaAndIdEstado(cedula, 1);
    }

    // 📌 Insertar evaluación usando el SP del package
    @Transactional
    public void agregarEvaluacionDocente(Long cedula,
                                         Long idTipoEvaluacion,
                                         Integer anio,
                                         BigDecimal calificacion,
                                         String comentarios) {

        Integer idEstado = 1; // ACTIVO

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_CEDULA", cedula)
                .addValue("P_ID_TIPO_EVALUACION", idTipoEvaluacion)
                .addValue("P_ANIO", anio)
                .addValue("P_CALIFICACION", calificacion)
                .addValue("P_COMENTARIOS", comentarios)
                .addValue("P_ID_ESTADO", idEstado);

        insertEvaluacionCall.execute(params);
    }

    // 📌 Eliminar evaluación usando el SP DELETE (cambia ID_ESTADO a 2)
    @Transactional
    public void eliminarEvaluacionDocente(Long cedula,
                                          Long idTipoEvaluacion,
                                          Integer anio) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_CEDULA", cedula)
                .addValue("P_ID_TIPO_EVALUACION", idTipoEvaluacion)
                .addValue("P_ANIO", anio);

        deleteEvaluacionCall.execute(params);
    }
}