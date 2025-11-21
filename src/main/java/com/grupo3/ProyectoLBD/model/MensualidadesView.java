package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FIDE_MENSUALIDADES_V")
public class MensualidadesView {
    @Id
    @Column(name = "ID_MENSUALIDAD")
    private Integer idMensualidad;

    @Column(name = "MES")
    private String mes; 
    
    @Column(name = "ESTADO_MENSUALIDAD")
    private String estadoMensualidad; //fecha en la que hizo la mensualidad

    @Column(name = "CEDULA_APODERADO")
    private Long cedulaApoderado;

    @Column(name = "APODERADO_NOMBRE_COMPLETO")
    private String apoderadoNombre;
    
    @Column(name = "CEDULA_INFANTE")
    private Long cedulaInfante;
    
    @Column(name = "INFANTE_NOMBRE_COMPLETO")
    private String infanteNombre;

    @Column(name = "ID_FACTURA")
    private Integer idFactura;

    @Column(name = "SERVICIO_DESCRIPCION")
    private String servicioDescripcion;

    @Column(name = "SERVICIO_MONTO")
    private double servicioMonto;

    @Column(name = "SERVICIO_MONTO_PAGADO")
    private double servicioMontoPagado;

    public Integer getIdMensualidad() {
        return idMensualidad;
    }

    public void setIdMensualidad(Integer idMensualidad) {
        this.idMensualidad = idMensualidad;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getEstadoMensualidad() {
        return estadoMensualidad;
    }

    public void setEstadoMensualidad(String estadoMensualidad) {
        this.estadoMensualidad = estadoMensualidad;
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

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getServicioDescripcion() {
        return servicioDescripcion;
    }

    public void setServicioDescripcion(String servicioDescripcion) {
        this.servicioDescripcion = servicioDescripcion;
    }

    public double getServicioMonto() {
        return servicioMonto;
    }

    public void setServicioMonto(double servicioMonto) {
        this.servicioMonto = servicioMonto;
    }

    public double getServicioMontoPagado() {
        return servicioMontoPagado;
    }

    public void setServicioMontoPagado(double servicioMontoPagado) {
        this.servicioMontoPagado = servicioMontoPagado;
    }

    
    
}
