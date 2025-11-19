package com.grupo3.ProyectoLBD.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo3.ProyectoLBD.model.MatriculasView;
import com.grupo3.ProyectoLBD.repository.MatriculasViewRepository;
import com.grupo3.ProyectoLBD.service.MatriculaService;

@Controller
@RequestMapping("/matriculas")
public class MatriculasController {

    private final MatriculasViewRepository matriculasViewRepository;
    private final MatriculaService matriculaService;

    public MatriculasController(MatriculasViewRepository matriculasViewRepository, MatriculaService matriculaService) {
        this.matriculasViewRepository = matriculasViewRepository;
        this.matriculaService = matriculaService;
    }

    @GetMapping
    public String verMatriculas(
            @RequestParam(required = false) Integer idMatricula,
            @RequestParam(required = false) String nombreInfante,
            Model model
    ) {

        List<MatriculasView> lista;

        String infante = (nombreInfante == null) ? "" : nombreInfante;

        // Filtro por nombre del infante o apoderado
        if (!infante.isEmpty()) {
            lista = matriculasViewRepository
                    .findByInfanteNombreContainingIgnoreCase(infante);

            // Filtro por ID de matrícula
        } else if (idMatricula != null) {
            lista = matriculasViewRepository.findByIdMatricula(idMatricula);

            // Mostrar todo
        } else {
            lista = matriculasViewRepository.findByEstadoMatriculaNot("INACTIVO");
        }

        model.addAttribute("listaMatriculas", lista);
        model.addAttribute("idMatricula", idMatricula);
        model.addAttribute("nombreInfante", nombreInfante);

        return "cuotasEscolares/matriculas";
    }

    @GetMapping("/ver/{id}")
    public String verDetalleMatricula(@PathVariable("id") Integer idMatricula, Model model) {

        MatriculasView matricula = matriculasViewRepository
                .findByIdMatricula(idMatricula)
                .stream()
                .findFirst()
                .orElse(null);

        if (matricula == null) {
            return "redirect:/matriculas?error=MatriculaNoEncontrada";
        }

        model.addAttribute("matricula", matricula);
        return "cuotasEscolares/detalleMatricula";
    }

    @PostMapping("/agregar")
    public String agregarMatricula(
            @RequestParam Integer idMatricula,
            @RequestParam Long cedulaApoderado,
            @RequestParam Long cedulaInfante,
            @RequestParam Integer idFactura,
            @RequestParam String fechaMatricula,
            @RequestParam Integer idEstado,
            RedirectAttributes redirectAttributes
    ) {

        Integer val = matriculaService.validarFactura(idFactura);

        if (val == 0) {
            redirectAttributes.addFlashAttribute("mensajeTipo", "danger");
            redirectAttributes.addFlashAttribute("mensaje", "La factura no es válida para una matrícula.");
            return "redirect:/matriculas";
        }

        if (val == -1) {
            redirectAttributes.addFlashAttribute("mensajeTipo", "danger");
            redirectAttributes.addFlashAttribute("mensaje", "Error al validar la factura.");
            return "redirect:/matriculas";
        }

        try {
            LocalDate fecha = LocalDate.parse(fechaMatricula); // Convierte de String a LocalDate

            matriculaService.insertarMatricula(
                    idMatricula,
                    cedulaApoderado,
                    cedulaInfante,
                    idFactura,
                    fecha,
                    idEstado
            );

            redirectAttributes.addFlashAttribute("mensajeTipo", "success");
            redirectAttributes.addFlashAttribute("mensaje", "Matrícula agregada correctamente.");
            return "redirect:/matriculas";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeTipo", "danger");
            redirectAttributes.addFlashAttribute("mensaje", "Error al insertar la matrícula.");
            return "redirect:/matriculas";
        }
    }

    @GetMapping("/editar/{idMatricula}")
    public String editarMatriculaForm(@PathVariable("idMatricula") Integer idMatricula, Model model) {
        MatriculasView m = matriculasViewRepository.findByIdMatricula(idMatricula).get(0);
        model.addAttribute("m", m);
        return "cuotasEscolares/editarMatricula";
    }

    @PostMapping("/actualizar/{idMatricula}")
    public String actualizarMatricula(
            @PathVariable Integer idMatricula,
            @RequestParam("fechaMatricula") LocalDate fechaMatricula,
            @RequestParam("idFactura") Integer idFactura,
            @RequestParam("idEstado") Integer idEstado,
            Model model
    ) {

        MatriculasView m = matriculasViewRepository.findByIdMatricula(idMatricula).get(0);

        // Validar factura
        Integer validacion = matriculaService.validarFactura(idFactura);

        if (validacion == null || validacion == 0) {
            model.addAttribute("mensajeTipo", "danger");
            model.addAttribute("mensaje", "La factura ingresada no es válida para matrícula.");
            model.addAttribute("m", m);
            return "cuotasEscolares/editarMatricula";
        }

        if (validacion == -1) {
            model.addAttribute("mensajeTipo", "danger");
            model.addAttribute("mensaje", "Ocurrió un error al validar la factura.");
            model.addAttribute("m", m);
            return "cuotasEscolares/editarMatricula";
        }

        // Llamar a procedimiento de actualización
        try {
            matriculaService.actualizarMatricula(
                    m.getIdMatricula(),
                    m.getCedulaApoderado(),
                    m.getCedulaInfante(),
                    idFactura,
                    fechaMatricula,
                    idEstado
            );
            model.addAttribute("mensajeTipo", "success");
            model.addAttribute("mensaje", "Matrícula actualizada exitosamente.");
            model.addAttribute("m", matriculasViewRepository.findByIdMatricula(idMatricula).get(0));
            return "cuotasEscolares/editarMatricula";

        } catch (Exception e) {
            model.addAttribute("mensajeTipo", "danger");
            model.addAttribute("mensaje", "Error al actualizar la matrícula en la base de datos.");
            model.addAttribute("m", m);
            return "cuotasEscolares/editarMatricula";
        }

    }

    @GetMapping("/eliminar/{idMatricula}")
    public String eliminarMatricula(@PathVariable("idMatricula") Integer idMatricula) {

        MatriculasView m = matriculasViewRepository.findByIdMatricula(idMatricula).get(0);

        try {
            matriculaService.eliminarMatricula(
                    m.getIdMatricula(),
                    m.getCedulaApoderado(),
                    m.getCedulaInfante()
            );
            return "redirect:/matriculas?eliminado=true";

        } catch (Exception e) {
            return "redirect:/matriculas?errorEliminar=true";
        }
    }

}
