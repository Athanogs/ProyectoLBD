package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FIDE_FORMACION_ACADEMICA_VW")
public class FormacionAcademicaView {

    @Id
    @Column(name = "ID_FORMACION")
    private Long idFormacion;

    @Column(name = "CEDULA")
    private Long cedula;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "INSTITUCION")
    private String institucion;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "FECHA_OBTENCION")
    private LocalDate fechaObtencion;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    @Column(name = "ESTADO")
    private String estado;

    // Getters y setters

    public Long getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(Long idFormacion) {
        this.idFormacion = idFormacion;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaObtencion() {
        return fechaObtencion;
    }

    public void setFechaObtencion(LocalDate fechaObtencion) {
        this.fechaObtencion = fechaObtencion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}