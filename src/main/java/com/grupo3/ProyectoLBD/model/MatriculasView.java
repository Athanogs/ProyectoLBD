package com.grupo3.ProyectoLBD.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FIDE_MATRICULAS_V")
public class MatriculasView {
    @Id
    @Column(name = "ID_MATRICULA")
    private Integer idMatricula;

    @Column(name = "FECHA_MATRICULA")
    private LocalDate fechaMatricula; 
    
    @Column(name = "ESTADO_MATRICULA")
    private String estadoMatricula; //fecha en la que hizo la matricula

    @Column(name = "CEDULA_APODERADO")
    private Long cedulaApoderado;

    @Column(name = "APODERADO_NOMBRE_COMPLETO")
    private String apoderadoNombre;
    
    @Column(name = "CEDULA_INFANTE")
    private Long cedulaInfante;
    
    @Column(name = "INFANTE_NOMBRE_COMPLETO")
    private String infanteNombre;

    @Column(name = "ID_FACTURA")
    private String idFactura;

    @Column(name = "SERVICIO_DESCRIPCION")
    private String servicioDescripcion;

    @Column(name = "SERVICIO_MONTO")
    private double servicioMonto;

    @Column(name = "SERVICIO_MONTO_PAGADO")
    private double servicioMontoPagado;

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
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

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
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


