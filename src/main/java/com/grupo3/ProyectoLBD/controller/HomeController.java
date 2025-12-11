package com.grupo3.ProyectoLBD.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo3.ProyectoLBD.security.CustomUserDetails;
import com.grupo3.ProyectoLBD.service.EstadisticasService;

@Controller
public class HomeController {
    private final EstadisticasService estadisticasService;

    public HomeController(EstadisticasService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @GetMapping("/home")
    public String home(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // Obtener nombre completo del usuario
        String nombreCompleto = userDetails.getUsuario().getPersona().getNombre() + " " +
                userDetails.getUsuario().getPersona().getApellidoPaterno() + " " +
                userDetails.getUsuario().getPersona().getApellidoMaterno();

        // Obtener nombre del rol
        String rol = userDetails.getUsuario().getPersona().getRol().getNombre().toUpperCase();

        model.addAttribute("nombreCompleto", nombreCompleto);
        model.addAttribute("rol", rol);

        // Obtener datos del service para las estadisticas
        model.addAttribute("totalAdministradores", estadisticasService.totalAdministradores());
        model.addAttribute("totalApoderados", estadisticasService.totalApoderados());
        model.addAttribute("totalDocentes", estadisticasService.totalDocentes());
        model.addAttribute("totalInfantes", estadisticasService.totalInfantes());
        model.addAttribute("totalGrupos", estadisticasService.totalGrupos());
        model.addAttribute("totalAsistencias", estadisticasService.totalAsistencias());
        model.addAttribute("totalFacturas", estadisticasService.totalFacturas());
        return "home";
    }
}