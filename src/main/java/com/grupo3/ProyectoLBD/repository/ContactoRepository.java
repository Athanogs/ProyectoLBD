package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, String> {

    // AGREGAR CORREO (ACTIVO)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO FIDE_EMAIL_TB (CEDULA, EMAIL, ID_ESTADO) VALUES (?1, ?2, 1)", nativeQuery = true)
    void guardarCorreo(String cedula, String email);

    // AGREGAR TELEFONO (ACTIVO)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO FIDE_TELEFONO_TB (CEDULA, NUM_TELEFONO, ID_ESTADO) VALUES (?1, ?2, 1)", nativeQuery = true)
    void guardarTelefono(String cedula, String telefono);

    // DESACTIVAR CORREO
    @Modifying
    @Transactional
    @Query(value = "UPDATE FIDE_EMAIL_TB SET ID_ESTADO = 2 WHERE CEDULA = ?1 AND EMAIL = ?2", nativeQuery = true)
    void eliminarCorreoEspecifico(String cedula, String email);

    // DESACTIVAR TELEFONO
    @Modifying
    @Transactional
    @Query(value = "UPDATE FIDE_TELEFONO_TB SET ID_ESTADO = 2 WHERE CEDULA = ?1 AND NUM_TELEFONO = ?2", nativeQuery = true)
    void eliminarTelefonoEspecifico(String cedula, String telefono);
}