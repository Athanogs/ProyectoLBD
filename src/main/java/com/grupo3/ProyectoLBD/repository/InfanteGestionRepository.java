package com.grupo3.ProyectoLBD.repository;

import com.grupo3.ProyectoLBD.model.InfanteGestionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfanteGestionRepository extends JpaRepository<InfanteGestionView, String> {

    // ============================
    // BUSCAR POR CÉDULA Y/O NOMBRE
    // ============================
    @Query("""
            SELECT i 
            FROM InfanteGestionView i
            WHERE (:cedula IS NULL OR :cedula = '' OR i.cedulaInfante LIKE %:cedula%)
              AND (
                    :nombre IS NULL OR :nombre = '' 
                    OR i.nombre LIKE %:nombre%
                    OR i.apellidoPaterno LIKE %:nombre%
                    OR i.apellidoMaterno LIKE %:nombre%
              )
            """)
    List<InfanteGestionView> buscarInfantes(
            @Param("cedula") String cedula,
            @Param("nombre") String nombre
    );


    // ============================
    // LISTAR POR ESTADO (1=Activo, 2=Inactivo)
    // ============================
    List<InfanteGestionView> findByEstado(Integer estado);

}