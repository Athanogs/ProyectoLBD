package com.grupo3.ProyectoLBD.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo3.ProyectoLBD.model.DetalleFacturaView;
import com.grupo3.ProyectoLBD.model.EncabezadoFacturaView;
import com.grupo3.ProyectoLBD.repository.EncabezadoFacturaViewRepository;
import com.grupo3.ProyectoLBD.service.FacturacionService;
import com.grupo3.ProyectoLBD.service.PagoService;


@Controller
@RequestMapping("/facturacion")
public class FacturacionController {

    private final PagoService pagoService;
    private final FacturacionService facturacionService;
    private final EncabezadoFacturaViewRepository encabezadoFacturaViewRepository;

    

    public FacturacionController(FacturacionService facturacionService, EncabezadoFacturaViewRepository encabezadoFacturaViewRepository, PagoService pagoService) {
        this.facturacionService = facturacionService;
        this.encabezadoFacturaViewRepository = encabezadoFacturaViewRepository;
        this.pagoService = pagoService;
    }

    @GetMapping("/opciones")
    public String mostrarOpciones() {
        return "/gestionFinanciera/facturacion/opciones";
    }

    @GetMapping("/facturacionServicios")
    public String mostrarModuloEmision(Model model) {
        model.addAttribute("metodosPago", facturacionService.obtenerMetodosPagoActivos());
        model.addAttribute("servicios", facturacionService.obtenerServiciosActivos());
        return "/gestionFinanciera/facturacion/facturacionServicios";
    }

    @GetMapping("/gestionFacturas")
    public String mostrarModuloGestion(
            @RequestParam(required = false) Long idFactura,
            @RequestParam(required = false) String cedulaApoderado,
            Model model
    ) {
        List<EncabezadoFacturaView> lista;
        String cedula = (cedulaApoderado == null) ? "" : cedulaApoderado.trim();
        if (!cedula.isEmpty()) {
            Long ced = Long.valueOf(cedula);
            lista = encabezadoFacturaViewRepository.findByCedulaApoderado(ced);

        } else if (idFactura != null) {
            lista = encabezadoFacturaViewRepository.findByIdFactura(idFactura);

        } else {
            lista = encabezadoFacturaViewRepository.findByEstadoNot("ANULADO");
        }

        model.addAttribute("listaFacturas", lista);
        model.addAttribute("idFactura", idFactura);
        model.addAttribute("cedulaApoderado", cedulaApoderado);

        return "/gestionFinanciera/facturacion/gestionFacturas";
    }

    @PostMapping("/guardar")
    public String guardarFactura(
            @RequestParam Long cedula,
            @RequestParam Long cedulaInfante,
            @RequestParam String fechaEmision,
            @RequestParam Integer idMetodoPago,
            @RequestParam BigDecimal subtotal,
            @RequestParam BigDecimal descuento,
            @RequestParam BigDecimal iva,
            @RequestParam BigDecimal montoTotal,
            @RequestParam("idServicio[]") List<Integer> idServicios,
            @RequestParam("montoPagado[]") List<BigDecimal> montos,
            RedirectAttributes redirect
    ) {
        Integer res = facturacionService.validarCedulaApoderado(cedula);
        if (res == 0) {
            redirect.addFlashAttribute("mensajeTipo", "danger");
            redirect.addFlashAttribute("mensaje", "La cédula del apoderado no es válida o no se encuentra registrado.");
            return "redirect:/facturacion/facturacionServicios";
        }

        if (res == -1) {
            redirect.addFlashAttribute("mensajeTipo", "danger");
            redirect.addFlashAttribute("mensaje", "Error al validar la cédula del apoderado.");
            return "redirect:/facturacion/facturacionServicios";
        }

        Integer num = facturacionService.validarCedulaInfante(cedulaInfante);
        if (num == 0) {
            redirect.addFlashAttribute("mensajeTipo", "danger");
            redirect.addFlashAttribute("mensaje", "La cédula del infante no es válida o no se encuentra registrado.");
            return "redirect:/facturacion/facturacionServicios";
        }

        if (num == -1) {
            redirect.addFlashAttribute("mensajeTipo", "danger");
            redirect.addFlashAttribute("mensaje", "Error al validar la cédula del infante.");
            return "redirect:/facturacion/facturacionServicios";
        }

        try {
            LocalDate fecha = LocalDate.parse(fechaEmision);
            String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            Long idFactura = facturacionService.insertarEncabezadoFactura(
                    cedula,
                    cedulaInfante,
                    fecha,
                    hora,
                    subtotal,
                    descuento,
                    iva,
                    montoTotal,
                    idMetodoPago
            );

            for (int i = 0; i < idServicios.size(); i++) {
                facturacionService.insertarDetalleFactura(idFactura, idServicios.get(i), montos.get(i));
                pagoService.insertarPago(idFactura, montos.get(i));
            }

            redirect.addFlashAttribute("mensajeTipo", "success");
            redirect.addFlashAttribute("mensaje", "Factura registrada con éxito.");
            return "redirect:/facturacion/facturacionServicios";
        } catch (Exception e) {
            redirect.addFlashAttribute("mensajeTipo", "danger");
            redirect.addFlashAttribute("mensaje", "Error al guardar la factura.");
            return "redirect:/facturacion/facturacionServicios";
        }
    }

    @GetMapping("/ver/{idFactura}")
    public String verFactura(@PathVariable long idFactura, Model model) {
        EncabezadoFacturaView encabezado = facturacionService.obtenerEncabezado(idFactura);
        List<DetalleFacturaView> detalle = facturacionService.obtenerDetalle(idFactura);

        model.addAttribute("factura", encabezado);
        model.addAttribute("detalle", detalle);

        return "/gestionFinanciera/facturacion/detalleFactura";
    }

    @GetMapping("/eliminar/{idFactura}")
    public String eliminarFactura(@PathVariable("idFactura") Long idFactura, RedirectAttributes redirect) {
        try {
            List<DetalleFacturaView> detalles = facturacionService.obtenerDetalle(idFactura);

            for (DetalleFacturaView d : detalles) {
                facturacionService.eliminarDetalleFactura(d.getIdFactura(), d.getIdServicio());
            }

            facturacionService.eliminarEncabezadoFactura(idFactura);
            return "redirect:/facturacion/gestionFacturas?eliminado=true";

        } catch (Exception e) {
            return "redirect:/facturacion/gestionFacturas?errorEliminar=true";
        }
    }
}
