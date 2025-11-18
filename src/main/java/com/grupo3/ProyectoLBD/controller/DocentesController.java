package com.grupo3.ProyectoLBD.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.grupo3.ProyectoLBD.dto.PersonaForm;
import com.grupo3.ProyectoLBD.model.*;
import com.grupo3.ProyectoLBD.repository.*;
import com.grupo3.ProyectoLBD.service.FormacionAcademicaService;
import com.grupo3.ProyectoLBD.service.CapacitacionService;

@Controller
@RequestMapping("/docentes")
public class DocentesController {

    private final PersonaUsuarioRolViewRepository personaViewRepo;
    private final FormacionAcademicaViewRepository formacionRepo;
    private final InstitucionRepository institucionRepo;
    private final CapacitacionViewRepository capacitacionRepo;
    private final ProveedorRepository proveedorRepo;
    private final FormacionAcademicaService formacionService;
    private final CapacitacionService capacitacionService;   // 👈 NUEVO

    public DocentesController(PersonaUsuarioRolViewRepository personaViewRepo,
                              FormacionAcademicaViewRepository formacionRepo,
                              InstitucionRepository institucionRepo,
                              CapacitacionViewRepository capacitacionRepo,
                              ProveedorRepository proveedorRepo,
                              FormacionAcademicaService formacionService,
                              CapacitacionService capacitacionService) {  // 👈 NUEVO
        this.personaViewRepo = personaViewRepo;
        this.formacionRepo = formacionRepo;
        this.institucionRepo = institucionRepo;
        this.capacitacionRepo = capacitacionRepo;
        this.proveedorRepo = proveedorRepo;
        this.formacionService = formacionService;
        this.capacitacionService = capacitacionService;       // 👈 NUEVO
    }

    // LISTAR DOCENTES
    @GetMapping
    public String verDocentes(@RequestParam(required = false) String cedula,
                              @RequestParam(required = false) String nombre,
                              Model model) {
        List<PersonaUsuarioRolView> lista;
        if (cedula != null && !cedula.isEmpty()) {
            Long ced = Long.valueOf(cedula);
            lista = personaViewRepo.findByCedula(ced);
        } else if (nombre != null && !nombre.isEmpty()) {
            lista = personaViewRepo
                    .findByNombreContainingIgnoreCaseOrApellidoPaternoContainingIgnoreCaseOrApellidoMaternoContainingIgnoreCase(
                            nombre, nombre, nombre);
        } else {
            lista = personaViewRepo.findbyIdRol();
        }
        model.addAttribute("listaUsuarios", lista);
        model.addAttribute("cedula", cedula);
        model.addAttribute("nombre", nombre);
        return "docentes";
    }

    // VER DETALLE DE UN DOCENTE
    @GetMapping("/ver/{cedula}")
    public String editarUsuarioForm(@PathVariable("cedula") Long cedula, Model model) {

        List<PersonaUsuarioRolView> resultado = personaViewRepo.findByCedula(cedula);
        if (resultado == null || resultado.isEmpty()) {
            return "redirect:/docentes?error=notfound";
        }

        PersonaUsuarioRolView v = resultado.get(0);

        PersonaForm form = new PersonaForm();
        form.setCedula(String.valueOf(v.getCedula()));
        form.setNombre(v.getNombre());
        form.setApellidoPaterno(v.getApellidoPaterno());
        form.setApellidoMaterno(v.getApellidoMaterno());
        form.setIdEstado(v.getIdEstado() == null ? 1 : v.getIdEstado());

        List<FormacionAcademicaView> formaciones =
                formacionRepo.findByCedulaAndIdEstado(cedula, 1);

        List<CapacitacionView> capacitaciones =
                capacitacionRepo.findByCedulaAndIdEstado(cedula, 1);

        List<Institucion> listaInstituciones = institucionRepo.findAll();
        List<Proveedor>   listaProveedores   = proveedorRepo.findAll();

        model.addAttribute("personaForm", form);
        model.addAttribute("modoEdicion", true);
        model.addAttribute("listaFormaciones", formaciones);
        model.addAttribute("listaInstituciones", listaInstituciones);
        model.addAttribute("listaCapacitaciones", capacitaciones);
        model.addAttribute("listaProveedores", listaProveedores);

        return "docentes/infoDocente";
    }

    // AGREGAR FORMACIÓN
    @PostMapping("/agregarFormacion")
    public String agregarFormacion(@RequestParam("cedula") Long cedula,
                                   @RequestParam("titulo") String titulo,
                                   @RequestParam("idInstitucion") Long idInstitucion,
                                   @RequestParam("fechaObtencion")
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaObtencion) {

        formacionService.agregarFormacionDocente(
                cedula, idInstitucion, titulo, fechaObtencion
        );

        return "redirect:/docentes/ver/" + cedula;
    }

    // ELIMINAR FORMACIÓN
    @GetMapping("/eliminarFormacion")
    public String eliminarFormacion(@RequestParam("cedula") Long cedula,
                                    @RequestParam("idFormacion") Long idFormacion) {

        formacionService.eliminarFormacionDocente(cedula, idFormacion);
        return "redirect:/docentes/ver/" + cedula;
    }

    // 🔹 AGREGAR CAPACITACIÓN
    @PostMapping("/agregarCapacitacion")
    public String agregarCapacitacion(@RequestParam("cedula") Long cedula,
                                      @RequestParam("tema") String tema,
                                      @RequestParam("idProveedor") Long idProveedor,
                                      @RequestParam("anio") Integer anio,
                                      @RequestParam("horas") Integer horas) {

        capacitacionService.agregarCapacitacionDocente(
                cedula, tema, idProveedor, anio, horas
        );

        return "redirect:/docentes/ver/" + cedula;
    }

    // 🔹 ELIMINAR CAPACITACIÓN (usa FIDE_CAPACITACION_DELETE_SP)
    @GetMapping("/eliminarCapacitacion")
    public String eliminarCapacitacion(@RequestParam("cedula") Long cedula,
                                       @RequestParam("idTema") Integer idTema,
                                       @RequestParam("idProveedor") Integer idProveedor) {

        capacitacionService.eliminarCapacitacionDocente(cedula, idTema, idProveedor);

        return "redirect:/docentes/ver/" + cedula;
    }
}