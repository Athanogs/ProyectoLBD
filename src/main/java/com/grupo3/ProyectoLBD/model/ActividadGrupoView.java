package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FIDE_ACTIVIDAD_GRUPO_VW")
public class ActividadGrupoView {

    @Id
    @Column(name = "ID_ACTIVIDAD")
    private Integer idActividad;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FECHA")
    private LocalDate fecha;

    @Column(name = "HORA")
    private String hora;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    @Column(name = "ESTADO_ACTIVIDAD")
    private String estadoActividad;

    @Column(name = "ID_GRUPO")
    private Integer idGrupo;

    @Column(name = "NOMBRE_GRUPO")
    private String nombreGrupo;

    @Column(name = "CEDULA_ENCARGADO")
    private Long cedulaEncargado;

    @Column(name = "ESTADO_GRUPO")
    private String estadoGrupo;

    // Getters y Setters

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // CAMBIO REALIZADO
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Long getCedulaEncargado() {
        return cedulaEncargado;
    }

    public void setCedulaEncargado(Long cedulaEncargado) {
        this.cedulaEncargado = cedulaEncargado;
    }

    public String getEstadoGrupo() {
        return estadoGrupo;
    }

    public void setEstadoGrupo(String estadoGrupo) {
        this.estadoGrupo = estadoGrupo;
    }
}

