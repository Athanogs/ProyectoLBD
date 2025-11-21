package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.model.FideInfanteTb;
import com.grupo3.ProyectoLBD.model.InfanteGestionView;
import com.grupo3.ProyectoLBD.service.InfanteGestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/infantes")
public class InfanteGestionController {

    @Autowired
    private InfanteGestionService service;

    // ============================
    // LISTAR ACTIVOS
    // ============================
    @GetMapping
    public String listarInfantes(Model model) {
        model.addAttribute("infantes", service.listarInfantes());
        return "infantes";
    }

    // ============================
    // LISTAR INACTIVOS (NUEVO)
    // ============================
    @GetMapping("/inactivos")
    public String listarInactivos(Model model) {

        // Lista solo los infantes con estado = 2
        model.addAttribute("infantes", service.listarInactivos());

        return "infantes"; // usamos la misma vista
    }

    // ============================
    // BUSCAR
    // ============================
    @GetMapping("/buscar")
    public String buscarInfantes(
            @RequestParam(required = false) String cedula,
            @RequestParam(required = false) String nombre,
            Model model) {

        if ((cedula == null || cedula.isEmpty()) &&
            (nombre == null || nombre.isEmpty())) {

            model.addAttribute("infantes", service.listarInfantes());
        } else {
            model.addAttribute("infantes",
                    service.buscarInfantes(cedula, nombre));
        }

        return "infantes";
    }

    // ============================
    // CREAR
    // ============================
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("infante", new FideInfanteTb());
        return "infantes-crear";
    }

    @PostMapping("/guardar")
    public String guardarInfante(@ModelAttribute FideInfanteTb infante) {
        service.guardarInfante(infante);
        return "redirect:/infantes";
    }

    // ============================
    // EDITAR
    // ============================
    @GetMapping("/editar/{cedula}")
    public String editarInfante(@PathVariable("cedula") String cedula, Model model) {

        InfanteGestionView vista = service.buscarPorCedula(cedula);

        FideInfanteTb infante = new FideInfanteTb();
        infante.setCedulaInfante(cedula);
        infante.setIdEstado(1); // activo

        model.addAttribute("datosVista", vista);
        model.addAttribute("infante", infante);

        return "infantes-editar";
    }

    // ============================
    // ACTUALIZAR
    // ============================
    @PostMapping("/actualizar")
    public String actualizarInfante(@ModelAttribute FideInfanteTb infante) {
        service.actualizarInfante(infante);
        return "redirect:/infantes";
    }

    // ============================
    // ELIMINAR (SE MARCA COMO INACTIVO)
    // ============================
    @GetMapping("/eliminar/{cedula}")
    public String eliminarInfante(@PathVariable("cedula") String cedula) {
        service.eliminarInfante(cedula); // esto pone id_estado = 2
        return "redirect:/infantes";
    }
}