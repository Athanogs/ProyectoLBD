package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.repository.ReportesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

    private final ReportesRepository repo;

    public ReportesController(ReportesRepository repo) {
        this.repo = repo;
    }

    // ================== MENU ==================
    @GetMapping("")
    public String menu() {
        return "reportes/menu";
    }

    // ================== FORMULARIOS ==================
    @GetMapping("/facturas/form")
    public String formFacturas() {
        return "reportes/facturas";
    }

    @GetMapping("/asistencia/form")
    public String formAsistencia() {
        return "reportes/asistencia";
    }

    @GetMapping("/actividades/form")
    public String formActividades() {
        return "reportes/actividades";
    }

    @GetMapping("/resumen/form")
    public String formResumen() {
        return "reportes/resumen";
    }

    // ================== CONSULTAS ==================
    @GetMapping("/facturas")
    public String facturas(@RequestParam long cedula, Model m) {
        m.addAttribute("titulo", "Facturas del Padre");
        m.addAttribute("datos", repo.facturasPadre(cedula));
        return "reportes/resultado";
    }

    @GetMapping("/asistencia")
public String asistencia(@RequestParam String inicio,
                         @RequestParam String fin,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "3") int size,
                         Model m) {

    var datos = repo.asistenciaRango(inicio, fin);

    int total = datos.size();
    int totalPaginas = (int) Math.ceil((double) total / size);

    int inicioIndex = (page - 1) * size;
    int finIndex = Math.min(inicioIndex + size, total);

    var datosPaginados = datos.subList(inicioIndex, finIndex);

    m.addAttribute("titulo", "Asistencia por rango");
    m.addAttribute("datos", datosPaginados);

    m.addAttribute("page", page);
    m.addAttribute("totalPaginas", totalPaginas);
    m.addAttribute("size", size);

    m.addAttribute("inicio", inicio);
    m.addAttribute("fin", fin);

    return "reportes/resultado";
}


    @GetMapping("/actividades")
    public String actividades(@RequestParam long cedula, Model m) {
        m.addAttribute("titulo", "Actividades del Infante");
        m.addAttribute("datos", repo.actividadesInfante(cedula));
        return "reportes/resultado";
    }

    @GetMapping("/resumen")
    public String resumen(@RequestParam long cedula, Model m) {
        m.addAttribute("titulo", "Resumen del Padre");
        m.addAttribute("datos", repo.resumenPadre(cedula));
        return "reportes/resultado";
    }
}
