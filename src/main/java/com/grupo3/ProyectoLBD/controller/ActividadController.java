package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.dto.ActividadForm;
import com.grupo3.ProyectoLBD.model.ActividadGrupoView;
import com.grupo3.ProyectoLBD.repository.ActividadGrupoViewRepository;
import com.grupo3.ProyectoLBD.repository.GrupoRepository;
import com.grupo3.ProyectoLBD.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadGrupoViewRepository actividadViewRepo;

    @Autowired
    private GrupoRepository grupoRepo; // Para checkboxes

    @Autowired
    private ActividadService actividadService;

    // LISTAR ACTIVIDADES
    @GetMapping
    public String listarActividades(@RequestParam(required = false) String descripcion,
                                    Model model) {
        List<ActividadGrupoView> lista;
        if (descripcion != null && !descripcion.isEmpty()) {
            lista = actividadViewRepo.findByDescripcionContainingIgnoreCase(descripcion);
        } else {
            lista = actividadViewRepo.findAll();
        }
        model.addAttribute("listaActividades", lista);
        model.addAttribute("descripcion", descripcion);
        return "actividades";
    }

    // FORMULARIO NUEVA ACTIVIDAD
    @GetMapping("/nueva")
    public String nuevaActividad(Model model) {
    ActividadForm form = new ActividadForm();
    form.setIdEstado(1);
    model.addAttribute("actividadForm", form);
    model.addAttribute("grupos", grupoRepo.findAll());
    model.addAttribute("modoEdicion", false);
    return "actividades/form";
    }

    // FORMULARIO EDITAR ACTIVIDAD
    @GetMapping("/editar/{idActividad}")
    public String editarActividad(@PathVariable Integer idActividad, Model model) {
        List<ActividadGrupoView> datos = actividadViewRepo.findByIdActividad(idActividad);
        if (datos.isEmpty()) {
            return "redirect:/actividades?error=notfound";
        }

        ActividadGrupoView a = datos.get(0);
        ActividadForm form = new ActividadForm();
        form.setIdActividad(a.getIdActividad());
        form.setDescripcion(a.getDescripcion());
        form.setFecha(a.getFecha());
        form.setHora(a.getHora());
        form.setObservaciones(a.getObservaciones());
        form.setIdEstado(a.getIdEstado());

        // Lista de grupos asociados
        List<Integer> gruposSeleccionados = datos.stream()
                                                 .map(ActividadGrupoView::getIdGrupo)
                                                 .collect(Collectors.toList());
        form.setIdGrupos(gruposSeleccionados);

        model.addAttribute("actividadForm", form);
        model.addAttribute("grupos", grupoRepo.findAll());
        model.addAttribute("modoEdicion", true);
        return "actividades/form";
    }

    // GUARDAR ACTIVIDAD
    @PostMapping("/guardar")
    public String guardarActividad(@ModelAttribute ActividadForm actividadForm, Model model) {
        try {
            if (actividadForm.getIdEstado() == null) {
                actividadForm.setIdEstado(1);
            }
            actividadService.crearActividad(actividadForm);
            return "redirect:/actividades?success";
        } catch (Exception e) {
            model.addAttribute("error", "Error creando actividad: " + e.getMessage());
            model.addAttribute("actividadForm", actividadForm);
            model.addAttribute("grupos", grupoRepo.findAll());
            model.addAttribute("modoEdicion", false);
            return "actividades/form";
        }
    }

     
    //ACTUALIZAR ACTIVIDAD
    @PostMapping("/actualizar")
    public String actualizarActividad(@ModelAttribute ActividadForm actividadForm, Model model) {
        try {
            if (actividadForm.getIdEstado() == null) {
                actividadForm.setIdEstado(1);
            }

            // Obtener lista de grupos existentes
            List<ActividadGrupoView> relaciones = actividadViewRepo.findByIdActividad(actividadForm.getIdActividad());
            List<Integer> gruposExistentes = relaciones.stream()
                                                       .map(ActividadGrupoView::getIdGrupo)
                                                       .collect(Collectors.toList());

            // Llamar al service con la lista de grupos existentes
            actividadService.actualizarActividad(actividadForm, gruposExistentes);

            return "redirect:/actividades?updated";
        } catch (Exception e) {
            model.addAttribute("error", "Error actualizando actividad: " + e.getMessage());
            model.addAttribute("actividadForm", actividadForm);
            model.addAttribute("grupos", grupoRepo.findAll());
            model.addAttribute("modoEdicion", true);
            return "actividades/form";
        }
    } 

 

    // ELIMINAR ACTIVIDAD + RELACIONES GRUPOS
    @GetMapping("/eliminar/{idActividad}")
    public String eliminarActividad(@PathVariable Integer idActividad,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Obtener lista de grupos asociados para eliminar relaciones
            List<ActividadGrupoView> relaciones = actividadViewRepo.findByIdActividad(idActividad);
            List<Integer> idGrupos = relaciones.stream()
                                               .map(ActividadGrupoView::getIdGrupo)
                                               .collect(Collectors.toList());

            actividadService.eliminarActividad(idActividad, idGrupos);
            redirectAttributes.addFlashAttribute("mensaje", "Actividad eliminada (estado = 2).");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }
        return "redirect:/actividades";
    }
}

