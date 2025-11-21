package com.grupo3.ProyectoLBD.service;

import com.grupo3.ProyectoLBD.model.FideInfanteTb;
import com.grupo3.ProyectoLBD.model.InfanteGestionView;
import com.grupo3.ProyectoLBD.repository.FideInfanteRepository;
import com.grupo3.ProyectoLBD.repository.InfanteGestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfanteGestionService {

    @Autowired
    private InfanteGestionRepository viewRepository; // Vista

    @Autowired
    private FideInfanteRepository tablaRepository; // Tabla real


    // ============================
    // LISTAR ACTIVOS (vista)
    // ============================
    public List<InfanteGestionView> listarInfantes() {
        return viewRepository.findByEstado(1);
    }


    // ============================
    // LISTAR INACTIVOS (vista)
    // ============================
    public List<InfanteGestionView> listarInactivos() {
        return viewRepository.findByEstado(2);
    }


    // ============================
    // BUSCAR EN LA VISTA
    // ============================
    public List<InfanteGestionView> buscarInfantes(String cedula, String nombre) {
        return viewRepository.buscarInfantes(cedula, nombre);
    }


    // ============================
    // BUSCAR SOLO POR CÉDULA
    // ============================
    public InfanteGestionView buscarPorCedula(String cedula) {
        return viewRepository.findById(cedula).orElse(null);
    }


    // ============================
    // CREAR EN TABLA REAL
    // ============================
    public void guardarInfante(FideInfanteTb infante) {
        infante.setIdEstado(1); // activo por defecto
        tablaRepository.save(infante);
    }


    // ============================
    // ACTUALIZAR
    // ============================
    public void actualizarInfante(FideInfanteTb infante) {
        tablaRepository.save(infante);
    }


    // ============================
    // ELIMINAR (CAMBIAR A INACTIVO)
    // ============================
    public void eliminarInfante(String cedula) {
        FideInfanteTb infante = tablaRepository.findById(cedula).orElse(null);

        if (infante != null) {
            infante.setIdEstado(2); // inactivo
            tablaRepository.save(infante);
        }
    }
}