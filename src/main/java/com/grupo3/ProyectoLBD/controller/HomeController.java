package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // Obtener nombre completo del usuario
        String nombreCompleto = userDetails.getUsuario().getPersona().getNombre() + " " +
                userDetails.getUsuario().getPersona().getApellidoPaterno() + " " +
                userDetails.getUsuario().getPersona().getApellidoMaterno();

        // Obtener NOMBRE DEL ROL
        String rol = userDetails.getUsuario().getPersona().getRol().getNombre().toUpperCase();

        model.addAttribute("nombreCompleto", nombreCompleto);
        model.addAttribute("rol", rol);

        return "home";
    }
}