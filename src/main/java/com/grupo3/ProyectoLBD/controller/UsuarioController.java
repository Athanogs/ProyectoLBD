package com.grupo3.ProyectoLBD.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo3.ProyectoLBD.dto.PersonaForm;
import com.grupo3.ProyectoLBD.model.PersonaUsuarioRolView;
import com.grupo3.ProyectoLBD.repository.CantonRepository;
import com.grupo3.ProyectoLBD.repository.DistritoRepository;
import com.grupo3.ProyectoLBD.repository.PaisRepository;
import com.grupo3.ProyectoLBD.repository.PersonaUsuarioRolViewRepository;
import com.grupo3.ProyectoLBD.repository.ProvinciaRepository;
import com.grupo3.ProyectoLBD.repository.RolRepository;
import com.grupo3.ProyectoLBD.repository.UsuarioRepository;
import com.grupo3.ProyectoLBD.security.CustomUserDetails;
import com.grupo3.ProyectoLBD.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final PersonaUsuarioRolViewRepository personaViewRepo;
    private final RolRepository rolRepository;
    private final PaisRepository paisRepository;
    private final ProvinciaRepository provinciaRepository;
    private final CantonRepository cantonRepository;
    private final DistritoRepository distritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public UsuarioController(PersonaUsuarioRolViewRepository personaViewRepo,
            RolRepository rolRepository,
            PaisRepository paisRepository,
            ProvinciaRepository provinciaRepository,
            CantonRepository cantonRepository,
            DistritoRepository distritoRepository,
            UsuarioRepository usuarioRepository,
            UsuarioService usuarioService) {
        this.personaViewRepo = personaViewRepo;
        this.rolRepository = rolRepository;
        this.paisRepository = paisRepository;
        this.provinciaRepository = provinciaRepository;
        this.cantonRepository = cantonRepository;
        this.distritoRepository = distritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String verUsuarios(
            @RequestParam(required = false) String cedula,
            @RequestParam(required = false) String nombre,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long cedulaActual = userDetails.getUsuario().getPersona().getCedula();


        List<PersonaUsuarioRolView> lista;
        if (cedula != null && !cedula.isEmpty()) {
            Long ced = Long.valueOf(cedula);  
            lista = personaViewRepo.findByCedula(ced);
        } else if (nombre != null && !nombre.isEmpty()) {
            lista = personaViewRepo
                    .findByNombreContainingIgnoreCaseOrApellidoPaternoContainingIgnoreCaseOrApellidoMaternoContainingIgnoreCase(
                            nombre, nombre, nombre);
        } else {
            lista = personaViewRepo.findbyIdEstado();
        }

        lista = lista.stream()
            .filter(u -> !u.getCedula().equals(cedulaActual))
            .toList();
            
        model.addAttribute("listaUsuarios", lista);
        model.addAttribute("cedula", cedula);
        model.addAttribute("nombre", nombre);
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuarioForm(Model model) {
        PersonaForm form = new PersonaForm();
        form.setIdEstado(1); 

        model.addAttribute("personaForm", form);
        model.addAttribute("roles", rolRepository.findAll());
        model.addAttribute("paises", paisRepository.findAll());
        model.addAttribute("provincias", provinciaRepository.findAll());
        model.addAttribute("cantones", cantonRepository.findAll());
        model.addAttribute("distritos", distritoRepository.findAll());
        model.addAttribute("modoEdicion", false); // creación

        return "usuarios/form"; 
    }

    @PostMapping("/guardar")
    public String guardarPersona(@ModelAttribute PersonaForm personaForm, Model model) {
        try {
            if (personaForm.getIdEstado() == null) {
                personaForm.setIdEstado(1);
            }
            usuarioService.crearPersonaCompleta(personaForm);
            return "redirect:/usuarios?success";
        } catch (Exception e) {
            model.addAttribute("error", "Error creando persona: " + e.getMessage());
            model.addAttribute("roles", rolRepository.findAll());
            model.addAttribute("paises", paisRepository.findAll());
            model.addAttribute("provincias", provinciaRepository.findAll());
            model.addAttribute("cantones", cantonRepository.findAll());
            model.addAttribute("distritos", distritoRepository.findAll());
            model.addAttribute("modoEdicion", false);
            return "usuarios/form";
        }
    }

    @GetMapping("/editar/{cedula}")
    public String editarUsuarioForm(@PathVariable("cedula") Long cedula, Model model) {

        List<PersonaUsuarioRolView> resultado = personaViewRepo.findByCedula(cedula);

        if (resultado == null || resultado.isEmpty()) {
            return "redirect:/usuarios?error=notfound";
        }

        PersonaUsuarioRolView v = resultado.get(0);

        PersonaForm form = new PersonaForm();
        form.setCedula(String.valueOf(v.getCedula()));
        form.setNombre(v.getNombre());
        form.setApellidoPaterno(v.getApellidoPaterno());
        form.setApellidoMaterno(v.getApellidoMaterno());

        if (v.getFechaNacimiento() != null) {
            form.setFechaNacimiento(v.getFechaNacimiento()); // LocalDate
        }

        form.setUsername(v.getUsername());
        form.setContrasena(v.getContrasena());

        form.setIdRol(v.getIdRol());
        form.setIdPais(v.getIdPais());
        form.setIdProvincia(v.getIdProvincia());
        form.setIdCanton(v.getIdCanton());
        form.setIdDistrito(v.getIdDistrito());
        form.setOtrasSenas(v.getOtrasSenas());
        form.setIdEstado(v.getIdEstado() == null ? 1 : v.getIdEstado());

        model.addAttribute("personaForm", form);
        model.addAttribute("roles", rolRepository.findAll());
        model.addAttribute("paises", paisRepository.findAll());
        model.addAttribute("provincias", provinciaRepository.findAll());
        model.addAttribute("cantones", cantonRepository.findAll());
        model.addAttribute("distritos", distritoRepository.findAll());
        model.addAttribute("modoEdicion", true);

        return "usuarios/form";
    }

    @PostMapping("/actualizar")
    public String actualizarPersona(@ModelAttribute PersonaForm personaForm, Model model) {
        try {
            if (personaForm.getIdEstado() == null) {
                personaForm.setIdEstado(1);
            }
            usuarioService.actualizarPersonaCompleta(personaForm);
            return "redirect:/usuarios?updated";
        } catch (Exception e) {
            model.addAttribute("error", "Error actualizando persona: " + e.getMessage());
            model.addAttribute("roles", rolRepository.findAll());
            model.addAttribute("paises", paisRepository.findAll());
            model.addAttribute("provincias", provinciaRepository.findAll());
            model.addAttribute("cantones", cantonRepository.findAll());
            model.addAttribute("distritos", distritoRepository.findAll());
            model.addAttribute("modoEdicion", true);
            return "usuarios/form";
        }
    }

    @GetMapping("/eliminar/{cedula}")
    public String eliminarUsuario(@PathVariable("cedula") Long cedula,
            RedirectAttributes redirectAttributes) {
        try {
            usuarioService.eliminarPersonaPorCedula(cedula);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado (estado = 2).");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }
}
