package com.grupo3.ProyectoLBD.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FIDE_ENCABEZADO_FACTURA_V")
public class EncabezadoFacturaView {
    @Id
    @Column(name = "ID_FACTURA")
    private Integer idFactura;

    @Column(name = "CEDULA_APODERADO")
    private Long cedulaApoderado;

    @Column(name = "APODERADO_NOMBRE_COMPLETO")
    private String apoderadoNombre;

    @Column(name = "CEDULA_INFANTE")
    private Long cedulaInfante;

    @Column(name = "INFANTE_NOMBRE_COMPLETO")
    private String infanteNombre;

    @Column(name = "FECHA_EMISION")
    private LocalDate fechaEmision;

    @Column(name = "HORA_EMISION")
    private String horaEmision;

    @Column(name = "SUBTOTAL")
    private double subtotal;

    @Column(name = "DESCUENTO")
    private double descuento;

    @Column(name = "IVA")
    private double iva;

    @Column(name = "MONTO_TOTAL")
    private double montoTotal;

    @Column(name = "METODO_PAGO")
    private String metodoPago;

    @Column(name = "ESTADO")
    private String estado;

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Long getCedulaApoderado() {
        return cedulaApoderado;
    }

    public void setCedulaApoderado(Long cedulaApoderado) {
        this.cedulaApoderado = cedulaApoderado;
    }

    public String getApoderadoNombre() {
        return apoderadoNombre;
    }

    public void setApoderadoNombre(String apoderadoNombre) {
        this.apoderadoNombre = apoderadoNombre;
    }

    public Long getCedulaInfante() {
        return cedulaInfante;
    }

    public void setCedulaInfante(Long cedulaInfante) {
        this.cedulaInfante = cedulaInfante;
    }

    public String getInfanteNombre() {
        return infanteNombre;
    }

    public void setInfanteNombre(String infanteNombre) {
        this.infanteNombre = infanteNombre;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(String horaEmision) {
        this.horaEmision = horaEmision;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
