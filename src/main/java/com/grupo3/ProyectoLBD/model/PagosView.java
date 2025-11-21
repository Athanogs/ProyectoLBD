package com.grupo3.ProyectoLBD.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(PagosViewId.class)
@Table(name = "FIDE_PAGOS_V")
public class PagosView {
    @Id
    @Column(name = "ID_PAGO")
    private Integer idPago;

    @Id
    @Column(name = "ID_FACTURA")
    private Integer idFactura;

    @Column(name = "FECHA_EMISION")
    private LocalDate fechaEmision;

    @Column(name = "CEDULA_APODERADO")
    private Long cedulaApoderado;

    @Column(name = "CEDULA_INFANTE")
    private Long cedulaInfante;

    @Column(name = "MONTO_TOTAL")
    private double montoTotal;

    @Column(name = "ESTADO")
    private String estado;

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Long getCedulaApoderado() {
        return cedulaApoderado;
    }

    public void setCedulaApoderado(Long cedulaApoderado) {
        this.cedulaApoderado = cedulaApoderado;
    }

    public Long getCedulaInfante() {
        return cedulaInfante;
    }

    public void setCedulaInfante(Long cedulaInfante) {
        this.cedulaInfante = cedulaInfante;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    
}
