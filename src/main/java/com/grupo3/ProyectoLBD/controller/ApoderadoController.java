package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.service.InfanteGestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo3.ProyectoLBD.security.CustomUserDetails;

@Controller
public class ApoderadoController {

    @Autowired
    private InfanteGestionService infanteService;

    // Vista principal del apoderado
    @GetMapping("/mis-infantes")
    public String verMisInfantes(@AuthenticationPrincipal CustomUserDetails user, Model model) {

        Long cedulaApoderado = user.getUsuario().getCedula();

        model.addAttribute("infantes",
            infanteService.listarInfantesPorApoderado(cedulaApoderado)
        );

        return "apoderado/mis-infantes"; 
    }
}