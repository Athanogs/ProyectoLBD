package com.grupo3.ProyectoLBD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gestionFinanciera")
public class GestionFinancieraController {
    @GetMapping
    public String mostrarOpciones() {
        return "gestionFinanciera/opciones";   // muestra menú de opciones
    }       
}
