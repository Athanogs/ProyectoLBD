package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.model.PersonaUsuarioRolView;
import com.grupo3.ProyectoLBD.repository.PersonaUsuarioRolViewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsuarioController {

    private final PersonaUsuarioRolViewRepository repo;

    public UsuarioController(PersonaUsuarioRolViewRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/usuarios")
    public String verUsuarios(
            @RequestParam(required = false) String cedula,
            @RequestParam(required = false) String nombre,
            Model model
    ) {
        List<PersonaUsuarioRolView> lista;
        if (cedula != null && !cedula.isEmpty()) {
            lista = repo.findByCedula(cedula);
        } else if (nombre != null && !nombre.isEmpty()) {
            lista = repo.findByNombreContainingIgnoreCaseOrApellidoPaternoContainingIgnoreCaseOrApellidoMaternoContainingIgnoreCase(nombre, nombre, nombre);
        } else {
            lista = repo.findAll();
        }
        model.addAttribute("listaUsuarios", lista);
        model.addAttribute("cedula", cedula);
        model.addAttribute("nombre", nombre);
        return "usuarios";
    }
}