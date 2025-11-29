package com.grupo3.ProyectoLBD.service;

import com.grupo3.ProyectoLBD.model.Contacto;
import com.grupo3.ProyectoLBD.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // ============================================================
    // LISTAR CONTACTOS DESDE LA VISTA
    // ============================================================
    public List<Contacto> listarContactos() {
        return contactoRepository.findAll();
    }


    // ============================================================
    // AGREGAR CORREO (REACTIVAR SI YA EXISTE)
    // ============================================================
    public boolean agregarCorreo(String cedula, String correo) {

        // ¿Existe el correo en estado ACTIVO?
        String sqlCheck = "SELECT COUNT(*) FROM FIDE_EMAIL_TB " +
                "WHERE CEDULA = ? AND EMAIL = ? AND ID_ESTADO = 1";

        Integer count = jdbcTemplate.queryForObject(sqlCheck, Integer.class, cedula, correo);
        if (count != null && count > 0) {
            return false; // ya existe ACTIVO → error de duplicado
        }

        // ¿Existe pero INACTIVO? → REACTIVAR
        String sqlReact = "UPDATE FIDE_EMAIL_TB " +
                "SET ID_ESTADO = 1 " +
                "WHERE CEDULA = ? AND EMAIL = ? AND ID_ESTADO = 2";

        int updated = jdbcTemplate.update(sqlReact, cedula, correo);
        if (updated > 0) {
            return true; // Se reactivó OK
        }

        // INSERT normal
        String sqlInsert = "INSERT INTO FIDE_EMAIL_TB (CEDULA, EMAIL, ID_ESTADO) VALUES (?, ?, 1)";
        jdbcTemplate.update(sqlInsert, cedula, correo);

        return true;
    }


    // ============================================================
    // AGREGAR TELEFONO (REACTIVAR SI YA EXISTE)
    // ============================================================
    public boolean agregarTelefono(String cedula, String telefono) {

        String sqlCheck = "SELECT COUNT(*) FROM FIDE_TELEFONO_TB " +
                "WHERE CEDULA = ? AND NUM_TELEFONO = ? AND ID_ESTADO = 1";

        Integer count = jdbcTemplate.queryForObject(sqlCheck, Integer.class, cedula, telefono);
        if (count != null && count > 0) {
            return false; // Ya existe activo
        }

        // Reactivar si existe en estado 2
        String sqlReact = "UPDATE FIDE_TELEFONO_TB " +
                "SET ID_ESTADO = 1 " +
                "WHERE CEDULA = ? AND NUM_TELEFONO = ? AND ID_ESTADO = 2";

        int updated = jdbcTemplate.update(sqlReact, cedula, telefono);
        if (updated > 0) {
            return true;
        }

        // Insert normal
        String sqlInsert = "INSERT INTO FIDE_TELEFONO_TB (CEDULA, NUM_TELEFONO, ID_ESTADO) VALUES (?, ?, 1)";
        jdbcTemplate.update(sqlInsert, cedula, telefono);

        return true;
    }


    // ============================================================
    // ELIMINAR (DESACTIVAR) CORREO
    // ============================================================
    public void eliminarCorreo(String cedula, String correo) {
        String sql = "UPDATE FIDE_EMAIL_TB SET ID_ESTADO = 2 " +
                "WHERE CEDULA = ? AND EMAIL = ?";
        jdbcTemplate.update(sql, cedula, correo);
    }


    // ============================================================
    // ELIMINAR (DESACTIVAR) TELEFONO
    // ============================================================
    public void eliminarTelefono(String cedula, String telefono) {
        String sql = "UPDATE FIDE_TELEFONO_TB SET ID_ESTADO = 2 " +
                "WHERE CEDULA = ? AND NUM_TELEFONO = ?";
        jdbcTemplate.update(sql, cedula, telefono);
    }
}