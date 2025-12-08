package com.grupo3.ProyectoLBD.controller;

import com.grupo3.ProyectoLBD.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contactos")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    // LISTAR CONTACTOS
    @GetMapping
    public String listarContactos(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "success", required = false) String success,
            Model model
    ) {
        model.addAttribute("contactos", contactoService.listarContactos());
        model.addAttribute("activePage", "contactos");
        model.addAttribute("error", error);
        model.addAttribute("success", success);
        return "contactos/lista";
    }

    // AGREGAR CORREO
    @PostMapping("/agregar-correo/{cedula}")
    public String agregarCorreo(
            @PathVariable String cedula,
            @RequestParam("nuevoCorreo") String nuevoCorreo
    ) {
        if (!contactoService.agregarCorreo(cedula, nuevoCorreo)) {
            return "redirect:/contactos?error=correo";
        }
        return "redirect:/contactos?success=correo-agregado";
    }

    // AGREGAR TELEFONO
    @PostMapping("/agregar-telefono/{cedula}")
    public String agregarTelefono(
            @PathVariable String cedula,
            @RequestParam("nuevoTelefono") String nuevoTelefono
    ) {
        if (!contactoService.agregarTelefono(cedula, nuevoTelefono)) {
            return "redirect:/contactos?error=telefono";
        }
        return "redirect:/contactos?success=telefono-agregado";
    }

    // ELIMINAR (DESACTIVAR) CORREO
    @PostMapping("/eliminar-correo/{cedula}")
    public String eliminarCorreo(
            @PathVariable String cedula,
            @RequestParam("correo") String correo
    ) {
        contactoService.eliminarCorreo(cedula, correo);
        return "redirect:/contactos?success=correo-eliminado";
    }

    // ELIMINAR (DESACTIVAR) TELEFONO
    @PostMapping("/eliminar-telefono/{cedula}")
    public String eliminarTelefono(
            @PathVariable String cedula,
            @RequestParam("telefono") String telefono
    ) {
        contactoService.eliminarTelefono(cedula, telefono);
        return "redirect:/contactos?success=telefono-eliminado";
    }
}