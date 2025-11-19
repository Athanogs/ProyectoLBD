package com.grupo3.ProyectoLBD.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo3.ProyectoLBD.model.MensualidadesView;
import com.grupo3.ProyectoLBD.repository.MensualidadesViewRepository;
import com.grupo3.ProyectoLBD.service.MensualidadService;

@Controller
@RequestMapping("/mensualidades")
public class MensualidadesController {

    private final MensualidadesViewRepository mensualidadesViewRepository;
    private final MensualidadService mensualidadService;

    public MensualidadesController(MensualidadesViewRepository mensualidadesViewRepository,
            MensualidadService mensualidadService) {
        this.mensualidadesViewRepository = mensualidadesViewRepository;
        this.mensualidadService = mensualidadService;
    }

    @GetMapping
    public String verMensualidades(
            @RequestParam(required = false) Integer idMensualidad,
            @RequestParam(required = false) String nombreInfante,
            Model model
    ) {

        List<MensualidadesView> lista;

        String infante = (nombreInfante == null) ? "" : nombreInfante;

        if (!infante.isEmpty()) {
            lista = mensualidadesViewRepository
                    .findByInfanteNombreContainingIgnoreCase(infante);

        } else if (idMensualidad != null) {
            lista = mensualidadesViewRepository.findByIdMensualidad(idMensualidad);

        } else {
            lista = mensualidadesViewRepository.findByEstadoMensualidadNot("INACTIVO");
        }

        model.addAttribute("listaMensualidades", lista);
        model.addAttribute("idMensualidad", idMensualidad);
        model.addAttribute("nombreInfante", nombreInfante);

        return "cuotasEscolares/mensualidades";
    }

    @GetMapping("/ver/{id}")
    public String verDetalleMensualidad(@PathVariable("id") Integer idMensualidad, Model model) {

        MensualidadesView mensualidad = mensualidadesViewRepository
                .findByIdMensualidad(idMensualidad)
                .stream()
                .findFirst()
                .orElse(null);

        if (mensualidad == null) {
            return "redirect:/mensualidades?error=MensualidadNoEncontrada";
        }

        model.addAttribute("mensualidad", mensualidad);
        return "cuotasEscolares/detalleMensualidad";
    }


    @PostMapping("/agregar")
    public String agregarMensualidad(
            @RequestParam Integer idMensualidad,
            @RequestParam Long cedulaApoderado,
            @RequestParam Long cedulaInfante,
            @RequestParam Integer idFactura,
            @RequestParam String mes,
            @RequestParam Integer idEstado,
            RedirectAttributes redirectAttributes
    ) {

        Integer val = mensualidadService.validarFactura(idFactura);

        if (val == 0) {
            redirectAttributes.addFlashAttribute("mensajeTipo", "danger");
            redirectAttributes.addFlashAttribute("mensaje", "La factura no es válida para una mensualidad.");
            return "redirect:/mensualidades";
        }

        if (val == -1) {
            redirectAttributes.addFlashAttribute("mensajeTipo", "danger");
            redirectAttributes.addFlashAttribute("mensaje", "Error al validar la factura.");
            return "redirect:/mensualidades";
        }

        try {
            mensualidadService.insertarMensualidad(
                    idMensualidad,
                    cedulaApoderado,
                    cedulaInfante,
                    idFactura,
                    mes,
                    idEstado
            );

            redirectAttributes.addFlashAttribute("mensajeTipo", "success");
            redirectAttributes.addFlashAttribute("mensaje", "Mensualidad registrada correctamente.");
            return "redirect:/mensualidades";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeTipo", "danger");
            redirectAttributes.addFlashAttribute("mensaje", "Error al insertar la mensualidad.");
            return "redirect:/mensualidades";
        }
    }

    @GetMapping("/editar/{idMensualidad}")
    public String editarMensualidadForm(@PathVariable("idMensualidad") Integer idMensualidad, Model model) {
        MensualidadesView m = mensualidadesViewRepository.findByIdMensualidad(idMensualidad).get(0);
        model.addAttribute("m", m);
        return "cuotasEscolares/editarMensualidad";
    }

    @PostMapping("/actualizar/{idMensualidad}")
    public String actualizarMensualidad(
            @PathVariable Integer idMensualidad,
            @RequestParam("idFactura") Integer idFactura,
            @RequestParam("mes") String mes,
            @RequestParam("idEstado") Integer idEstado,
            Model model
    ) {

        MensualidadesView m = mensualidadesViewRepository.findByIdMensualidad(idMensualidad).get(0);

        Integer validacion = mensualidadService.validarFactura(idFactura);

        if (validacion == null || validacion == 0) {
            model.addAttribute("mensajeTipo", "danger");
            model.addAttribute("mensaje", "La factura ingresada no es válida para mensualidad.");
            model.addAttribute("m", m);
            return "cuotasEscolares/editarMensualidad";
        }

        if (validacion == -1) {
            model.addAttribute("mensajeTipo", "danger");
            model.addAttribute("mensaje", "Ocurrió un error al validar la factura.");
            model.addAttribute("m", m);
            return "cuotasEscolares/editarMensualidad";
        }

        // Llamar a procedimiento de actualización
        try {
            mensualidadService.actualizarMensualidad(
                    m.getIdMensualidad(),
                    m.getCedulaApoderado(),
                    m.getCedulaInfante(),
                    m.getIdFactura(),
                    mes,
                    idEstado
            );
            model.addAttribute("mensajeTipo", "success");
            model.addAttribute("mensaje", "Mensualidad actualizada exitosamente.");
            model.addAttribute("m", mensualidadesViewRepository.findByIdMensualidad(idMensualidad).get(0));
            return "cuotasEscolares/editarMensualidad";

        } catch (Exception e) {
            model.addAttribute("mensajeTipo", "danger");
            model.addAttribute("mensaje", "Error al actualizar la mensualidad en la base de datos.");
            model.addAttribute("m", m);
            return "cuotasEscolares/editarMensualidad";
        }

    }

    @GetMapping("/eliminar/{idMensualidad}")
    public String eliminarMensualidad(@PathVariable("idMensualidad") Integer idMensualidad) {

        MensualidadesView m = mensualidadesViewRepository.findByIdMensualidad(idMensualidad).get(0);

        try {
            mensualidadService.eliminarMensualidad(
                    m.getIdMensualidad(),
                    m.getCedulaApoderado(),
                    m.getCedulaInfante(),
                    m.getIdFactura()
            );
            return "redirect:/mensualidades?eliminado=true";

        } catch (Exception e) {
            return "redirect:/mensualidades?errorEliminar=true";
        }
    }
}
