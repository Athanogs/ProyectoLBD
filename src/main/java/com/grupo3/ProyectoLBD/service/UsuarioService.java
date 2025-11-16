package com.grupo3.ProyectoLBD.service;

import com.grupo3.ProyectoLBD.dto.PersonaForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioService {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall createPersonaFullCall;
    private final SimpleJdbcCall updatePersonaFullCall;
    private final SimpleJdbcCall deletePersonaCall;

    public UsuarioService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.createPersonaFullCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_CREATE_PERSONA_FULL_SP");

        this.updatePersonaFullCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_UPDATE_PERSONA_FULL_SP");

        this.deletePersonaCall = new SimpleJdbcCall(this.jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LBD_PCK")
                .withProcedureName("FIDE_PERSONA_DELETE_SP");
    }

    // ------------------ CREAR ------------------
    public void crearPersonaCompleta(PersonaForm form) {
        Map<String, Object> params = buildParams(form, false);
        createPersonaFullCall.execute(params);
    }

    // ------------------ ACTUALIZAR ------------------
    public void actualizarPersonaCompleta(PersonaForm form) {
        Map<String, Object> params = buildParams(form, true);
        updatePersonaFullCall.execute(params);
    }

    // ------------------ ELIMINAR (ID_ESTADO = 2) ------------------
    public void eliminarPersonaPorCedula(Long cedula) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", cedula);   // el parámetro del SP
        deletePersonaCall.execute(params);
    }

    /**
     * Nota: esto construye el mapa de parámetros para los SP.
     * Si es actualización y la contraseña viene en blanco,
    * se mantiene la contraseña actual de la BD. TENGO QUE REVISAR ESTO PORQUE NO FUNCIONA BIEN
     */
    private Map<String, Object> buildParams(PersonaForm form, boolean esActualizacion) {
        Map<String, Object> params = new HashMap<>();

        params.put("P_CEDULA", form.getCedula());
        params.put("P_NOMBRE", form.getNombre());
        params.put("P_APELLIDO_PATERNO", form.getApellidoPaterno());
        params.put("P_APELLIDO_MATERNO", form.getApellidoMaterno());
        params.put("P_USERNAME", form.getUsername());

        // ----- LÓGICA ESPECIAL PARA CONTRASEÑA -----
        String nuevaContrasena = form.getContrasena();
        if (esActualizacion) {
            if (nuevaContrasena == null || nuevaContrasena.isBlank()) {
                // Buscar la contraseña actual en la tabla de usuarios
                // AJUSTA nombres de tabla/columna si son distintos
                String contrasenaActual = jdbcTemplate.queryForObject(
                        "SELECT CONTRASENA " +
                        "FROM FIDE_USUARIO_TB " +
                        "WHERE CEDULA = ?",
                        String.class,
                        form.getCedula()
                );
                params.put("P_CONTRASENA", contrasenaActual);
            } else {
                //El usuario escribió una nueva contraseña entonces se se manda esa
                params.put("P_CONTRASENA", nuevaContrasena);
            }
        } else {
            //En creación la contraseña es obligatoria
            params.put("P_CONTRASENA", nuevaContrasena);
        }

        //Fecha de nacimiento
        params.put("P_FECHA_NACIMIENTO",
                form.getFechaNacimiento() == null
                        ? null
                        : Date.valueOf(form.getFechaNacimiento()));

        //IDs y otros campos (NECESITO REVISAR ESTO PORQUE FALLA EN UPDATE)
        params.put("P_ID_ROL",        form.getIdRol());
        params.put("P_ID_PAIS",       form.getIdPais());
        params.put("P_ID_PROVINCIA",  form.getIdProvincia());
        params.put("P_ID_CANTON",     form.getIdCanton());
        params.put("P_ID_DISTRITO",   form.getIdDistrito());
        params.put("P_OTRAS_SENAS",   form.getOtrasSenas() == null ? "" : form.getOtrasSenas());
        params.put("P_ID_ESTADO",     form.getIdEstado() == null ? 1 : form.getIdEstado());

        return params;
    }
}