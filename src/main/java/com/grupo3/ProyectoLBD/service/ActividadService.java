package com.grupo3.ProyectoLBD.service;

import com.grupo3.ProyectoLBD.dto.ActividadForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActividadService {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcCall insertActividadCall;
    private final SimpleJdbcCall updateActividadCall;
    private final SimpleJdbcCall deleteActividadCall;

    private final SimpleJdbcCall insertActividadGrupoCall;
    private final SimpleJdbcCall updateActividadGrupoCall;
    private final SimpleJdbcCall deleteActividadGrupoCall;

    public ActividadService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        String packageName = "FIDE_PROYECTO_LBD_PCK";

        // SPs de actividad
        this.insertActividadCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ACTIVIDAD_INSERT_SP");

        this.updateActividadCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ACTIVIDAD_UPDATE_SP");

        this.deleteActividadCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ACTIVIDAD_DELETE_SP");

        // SPs de actividad-por-grupo
        this.insertActividadGrupoCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ACTIVIDAD_POR_GRUPO_INSERT_SP");

        this.updateActividadGrupoCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ACTIVIDAD_POR_GRUPO_UPDATE_SP");

        this.deleteActividadGrupoCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName(packageName)
                .withProcedureName("FIDE_ACTIVIDAD_POR_GRUPO_DELETE_SP");
    }


 // CREAR ACTIVIDAD + ASIGNAR GRUPOS
public void crearActividad(ActividadForm form) {

    Map<String, Object> params = buildParamsActividad(form);

    // Ejecutar SP que incluye el parámetro OUT
    Map<String, Object> result = insertActividadCall.execute(params);

    // Recuperar el ID devuelto por Oracle (que viene como BigDecimal)
    BigDecimal idGeneradoBD = (BigDecimal) result.get("O_ID_ACTIVIDAD");
    Integer nuevoId = idGeneradoBD.intValue();  // ← Conversión correcta

    // Guardarlo en el form
    form.setIdActividad(nuevoId);

    // Insertar los grupos seleccionados
    if (form.getIdGrupos() != null) {
        for (Integer idGrupo : form.getIdGrupos()) {
            Map<String, Object> paramsGrupo = new HashMap<>();
            paramsGrupo.put("P_ID_ACTIVIDAD", nuevoId);
            paramsGrupo.put("P_ID_GRUPO", idGrupo);
            paramsGrupo.put("P_ID_ESTADO", 1);
            insertActividadGrupoCall.execute(paramsGrupo);
        }
    }
}




     //ACTUALIZAR ACTIVIDAD + GRUPOS
    public void actualizarActividad(ActividadForm form, List<Integer> gruposExistentes) {
        Map<String, Object> params = buildParamsActividad(form);
        updateActividadCall.execute(params);

        if (form.getIdGrupos() != null) {
            // Insertar o actualizar grupos seleccionados
            for (Integer idGrupo : form.getIdGrupos()) {
                if (gruposExistentes.contains(idGrupo)) {
                    // Actualiza estado a activo
                    Map<String, Object> paramsGrupo = new HashMap<>();
                    paramsGrupo.put("P_ID_ACTIVIDAD", form.getIdActividad());
                    paramsGrupo.put("P_ID_GRUPO", idGrupo);
                    paramsGrupo.put("P_ID_ESTADO", 1);
                    updateActividadGrupoCall.execute(paramsGrupo);
                } else {
                    // Inserta nueva relación
                    Map<String, Object> paramsGrupo = new HashMap<>();
                    paramsGrupo.put("P_ID_ACTIVIDAD", form.getIdActividad());
                    paramsGrupo.put("P_ID_GRUPO", idGrupo);
                    paramsGrupo.put("P_ID_ESTADO", 1);
                    insertActividadGrupoCall.execute(paramsGrupo);
                }
            }

            // Cambiar a estado = 2 los grupos que fueron quitados
            for (Integer idGrupo : gruposExistentes) {
                if (!form.getIdGrupos().contains(idGrupo)) {
                 Map<String, Object> paramsGrupo = new HashMap<>();
                paramsGrupo.put("P_ID_ACTIVIDAD", form.getIdActividad());
                paramsGrupo.put("P_ID_GRUPO", idGrupo);
                paramsGrupo.put("P_ID_ESTADO", 2); // poner inactivo
                updateActividadGrupoCall.execute(paramsGrupo); // usar update en vez de delete
    }
}
        }
    }



    // ELIMINAR ACTIVIDAD + RELACIONES GRUPOS
    public void eliminarActividad(Integer idActividad, List<Integer> grupos) {
        deleteActividadCall.execute(Map.of("P_ID_ACTIVIDAD", idActividad));

        if (grupos != null) {
            for (Integer idGrupo : grupos) {
                Map<String, Object> paramsGrupo = new HashMap<>();
                paramsGrupo.put("P_ID_ACTIVIDAD", idActividad);
                paramsGrupo.put("P_ID_GRUPO", idGrupo);
                deleteActividadGrupoCall.execute(paramsGrupo);
            }
        }
    }

    // ARMADO DE PARÁMETROS ACTIVIDAD
    private Map<String, Object> buildParamsActividad(ActividadForm form) {
    Map<String, Object> params = new HashMap<>();
    params.put("P_ID_ACTIVIDAD", form.getIdActividad());
    params.put("P_DESCRIPCION", form.getDescripcion());
    params.put("P_FECHA", form.getFecha() == null ? null : Date.valueOf(form.getFecha()));

    // --- CORRECCIÓN IMPORTANTE ---
    params.put("P_HORA", form.getHora()); // VARCHAR2 no debe convertirse a Time

    params.put("P_OBSERVACIONES", form.getObservaciones());
    params.put("P_ID_ESTADO", form.getIdEstado() == null ? 1 : form.getIdEstado());
    return params;
}

    
}
