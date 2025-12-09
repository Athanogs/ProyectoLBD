package com.grupo3.ProyectoLBD.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo3.ProyectoLBD.model.PagosView;
import com.grupo3.ProyectoLBD.repository.PagosViewRepository;
import com.grupo3.ProyectoLBD.service.PagoService;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    private final PagosViewRepository pagosViewRepository;
    private final PagoService pagoService;

    public PagoController(PagosViewRepository pagosViewRepository, PagoService pagoService) {
        this.pagosViewRepository = pagosViewRepository;
        this.pagoService = pagoService;
    }

    @GetMapping
    public String verPagos(
            @RequestParam(required = false) Integer idPago,
            @RequestParam(required = false) String cedulaApoderado,
            Model model
    ) {

        List<PagosView> lista;
        String cedula = (cedulaApoderado == null) ? "" : cedulaApoderado.trim();

        if (!cedula.isEmpty()) {
            Long ced = Long.valueOf(cedula);
            lista = pagosViewRepository.findByCedulaApoderado(ced);

        } else if (idPago != null) {
            lista = pagosViewRepository.findByIdPago(idPago);

        } else {
            lista = pagosViewRepository.findByEstadoNot("ANULADO");
        }

        model.addAttribute("listaPagos", lista);
        model.addAttribute("idPago", idPago);
        model.addAttribute("cedulaApoderado", cedulaApoderado);

        return "gestionFinanciera/pagos/pagos";
    }

    @GetMapping("/eliminar/{idPago}/{idFactura}")
    public String eliminarPago(
            @PathVariable Integer idPago,
            @PathVariable Long idFactura
    ) {
        try {
            pagoService.eliminarPago(idPago, idFactura);
            return "redirect:/pagos?eliminado=true";
            
        } catch (Exception e) {
            return "redirect:/pagos?errorEliminar=true";            
        }
    }
}
