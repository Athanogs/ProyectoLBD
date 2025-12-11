package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.repository.AuditoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auditoria")
public class AuditoriaController {

    private final AuditoriaRepository repo;

    public AuditoriaController(AuditoriaRepository repo) {
        this.repo = repo;
    }

    // ================== MENU PRINCIPAL ==================
    @GetMapping("")
    public String menu() {
        return "auditoria/menu";
    }

    // ================== AUDITORÍA GENERAL ==================
    @GetMapping("/general")
    public String auditoriaGeneral(Model m) {
        m.addAttribute("titulo", "Auditoría General del Sistema");
        m.addAttribute("datos", repo.auditoriaGeneral());
        return "auditoria/resultado";
    }

    // ================== AUDITORÍA POR TABLA ==================
    @GetMapping("/tabla/form")
    public String formPorTabla(Model m) {
        m.addAttribute("tablas", repo.listarTablasAuditadas());
        return "auditoria/porTabla";
    }

    @GetMapping("/tabla")
    public String auditoriaPorTabla(@RequestParam String nombreTabla, Model m) {
        m.addAttribute("titulo", "Auditoría de Tabla: " + nombreTabla);
        m.addAttribute("datos", repo.auditoriaPorTabla(nombreTabla));
        return "auditoria/resultado";
    }

    // ================== AUDITORÍA ESPECÍFICA ==================
    @GetMapping("/personas")
    public String auditoriaPersonas(Model m) {
        m.addAttribute("titulo", "Auditoría de Personas");
        m.addAttribute("datos", repo.auditoriaPersonas());
        return "auditoria/resultado";
    }

    @GetMapping("/infantes")
    public String auditoriaInfantes(Model m) {
        m.addAttribute("titulo", "Auditoría de Infantes");
        m.addAttribute("datos", repo.auditoriaInfantes());
        return "auditoria/resultado";
    }

    @GetMapping("/facturas")
    public String auditoriaFacturas(Model m) {
        m.addAttribute("titulo", "Auditoría de Facturas");
        m.addAttribute("datos", repo.auditoriaFacturas());
        return "auditoria/resultado";
    }

    @GetMapping("/matriculas")
    public String auditoriaMatriculas(Model m) {
        m.addAttribute("titulo", "Auditoría de Matrículas");
        m.addAttribute("datos", repo.auditoriaMatriculas());
        return "auditoria/resultado";
    }

    @GetMapping("/actividades")
    public String auditoriaActividades(Model m) {
        m.addAttribute("titulo", "Auditoría de Actividades");
        m.addAttribute("datos", repo.auditoriaActividades());
        return "auditoria/resultado";
    }

    @GetMapping("/pagos")
    public String auditoriaPagos(Model m) {
        m.addAttribute("titulo", "Auditoría de Pagos");
        m.addAttribute("datos", repo.auditoriaPagos());
        return "auditoria/resultado";
    }
}