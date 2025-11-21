package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.dto.AsistenciaForm;
import com.grupo3.ProyectoLBD.model.RegistroAsistenciaView;
import com.grupo3.ProyectoLBD.repository.RegistroAsistenciaViewRepository;
import com.grupo3.ProyectoLBD.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private RegistroAsistenciaViewRepository asistenciaRepo;

    @Autowired
    private AsistenciaService asistenciaService;

    // LISTAR ASISTENCIAS
    @GetMapping
    public String listarAsistencias(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String cedula,
            Model model) {

        List<RegistroAsistenciaView> lista;

        if (cedula != null && !cedula.isEmpty()) {
            try {
                Long cedulaLong = Long.parseLong(cedula);
                lista = asistenciaRepo.findByCedulaInfante(cedulaLong);
            } catch (NumberFormatException e) {
                lista = List.of();
            }
        } else if (nombre != null && !nombre.isEmpty()) {
            lista = asistenciaRepo.buscarPorNombreApellidoOCedula(nombre);
        } else {
            lista = asistenciaRepo.findAll();
        }

        model.addAttribute("listaAsistencias", lista);
        model.addAttribute("nombre", nombre);
        model.addAttribute("cedula", cedula);
        return "asistencias";
    }

    // FORMULARIO NUEVA ASISTENCIA
    @GetMapping("/nueva")
    public String nuevaAsistencia(Model model) {
        AsistenciaForm form = new AsistenciaForm();
        form.setIdEstado(1); 
        model.addAttribute("asistenciaForm", form);
        model.addAttribute("modoEdicion", false);
        return "asistencias/form";
    }

    @GetMapping("/editar/{cedula}/{fecha}")
    public String editarAsistencia(@PathVariable Long cedula,
                               @PathVariable String fecha,
                               Model model) {

    LocalDate fechaAsis = LocalDate.parse(fecha);

    RegistroAsistenciaView asistencia = asistenciaRepo.findById(
            new com.grupo3.ProyectoLBD.model.RegistroAsistenciaId(cedula, fechaAsis)
    ).orElse(null);

    if (asistencia == null) {
        return "redirect:/asistencias?error=notfound";
    }

    AsistenciaForm form = new AsistenciaForm();

    // DATOS PERSONALES COMPLETOS
    form.setCedulaInfante(asistencia.getCedulaInfante());
    form.setNombreInfante(asistencia.getNombreInfante());
    form.setApellidoPaterno(asistencia.getApellidoPaterno());
    form.setApellidoMaterno(asistencia.getApellidoMaterno());
    form.setFechaNacimiento(asistencia.getFechaNacimiento());

    // DATOS DE ASISTENCIA
    form.setFechaAsistencia(asistencia.getFechaAsistencia());
    form.setHoraEntrada(asistencia.getHoraEntrada());
    form.setHoraSalida(asistencia.getHoraSalida());
    form.setObservaciones(asistencia.getObservaciones());

    model.addAttribute("asistenciaForm", form);
    model.addAttribute("modoEdicion", true);

    return "asistencias/form";
}

    // GUARDAR ASISTENCIA (solo envía los 6 parámetros del SP)
    @PostMapping("/guardar")
    public String guardarAsistencia(@ModelAttribute AsistenciaForm form,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {

        try {
            AsistenciaForm envio = new AsistenciaForm();
            envio.setCedulaInfante(form.getCedulaInfante());
            envio.setFechaAsistencia(form.getFechaAsistencia());
            envio.setHoraEntrada(form.getHoraEntrada());
            envio.setHoraSalida(form.getHoraSalida());
            envio.setObservaciones(form.getObservaciones());
            envio.setIdEstado(form.getIdEstado() != null ? form.getIdEstado() : 1);

            asistenciaService.crearAsistencia(envio);

            return "redirect:/asistencias?success";

        } catch (Exception e) {
            model.addAttribute("error", "Error creando asistencia: " + e.getMessage());
            model.addAttribute("asistenciaForm", form);
            model.addAttribute("modoEdicion", false);
            return "asistencias/form";
        }
    }

    // ACTUALIZAR ASISTENCIA
    @PostMapping("/actualizar")
    public String actualizarAsistencia(@ModelAttribute AsistenciaForm form,
                                       Model model) {

        try {
            asistenciaService.actualizarAsistencia(form);
            return "redirect:/asistencias?updated";

        } catch (Exception e) {
            model.addAttribute("error", "Error actualizando: " + e.getMessage());
            model.addAttribute("asistenciaForm", form);
            model.addAttribute("modoEdicion", true);
            return "asistencias/form";
        }
    }

    // ELIMINAR ASISTENCIA
    @GetMapping("/eliminar/{cedula}/{fecha}")
    public String eliminarAsistencia(@PathVariable Long cedula,
                                     @PathVariable String fecha,
                                     RedirectAttributes redirect) {

        try {
            LocalDate fechaAsis = LocalDate.parse(fecha);
            asistenciaService.eliminarAsistencia(cedula, fechaAsis);
            redirect.addFlashAttribute("mensaje", "Registro marcado como inactivo.");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }

        return "redirect:/asistencias";
    }
}
