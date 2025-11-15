package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.model.Estado;
import com.grupo3.ProyectoLBD.repository.EstadoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EstadoController {

    private final EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @GetMapping("/estados")
    public String verEstados(Model model) {
        List<Estado> lista = estadoRepository.findAll();
        System.out.println(">>> Estados encontrados: " + lista.size());
        for (Estado e : lista) {
            System.out.println(">>> Estado: " + e.getIdEstado() + " - " + e.getDescripcion());
        }

        model.addAttribute("estados", lista);
        return "estados";
    }

    @GetMapping("/api/estados")
    @ResponseBody
    public List<Estado> estadosJson() {
        return estadoRepository.findAll();
    }
}