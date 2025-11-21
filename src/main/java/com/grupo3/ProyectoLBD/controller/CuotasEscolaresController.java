package com.grupo3.ProyectoLBD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cuotasEscolares")
public class CuotasEscolaresController {
    @GetMapping
    public String mostrarOpciones() {
        return "cuotasEscolares/opciones";   // muestra menú de opciones
    }    
}
