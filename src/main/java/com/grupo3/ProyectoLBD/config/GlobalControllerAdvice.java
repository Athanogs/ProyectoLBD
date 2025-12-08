package com.grupo3.ProyectoLBD.config;

import com.grupo3.ProyectoLBD.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void agregarDatosUsuario(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Evita error en páginas públicas o sin login
        if (auth == null || !(auth.getPrincipal() instanceof CustomUserDetails)) {
            return;
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        try {
            String nombreCompleto =
                    userDetails.getUsuario().getPersona().getNombre() + " " +
                    userDetails.getUsuario().getPersona().getApellidoPaterno() + " " +
                    userDetails.getUsuario().getPersona().getApellidoMaterno();

            String rol = userDetails.getUsuario().getPersona().getRol().getNombre().toUpperCase();

            model.addAttribute("nombreCompleto", nombreCompleto);
            model.addAttribute("rol", rol);

        } catch (Exception ignored) {}
    }
}