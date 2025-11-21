package com.grupo3.ProyectoLBD.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ActividadForm {

    private Integer idActividad;
    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    
    private String hora;

    private String observaciones;

    // Lista de grupos seleccionados (checkboxes)
    private List<Integer> idGrupos;

    // Por defecto ACTIVO
    private Integer idEstado;

    // ===== Getters y Setters =====

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

    public List<Integer> getIdGrupos() {
        return idGrupos;
    }
    public void setIdGrupos(List<Integer> idGrupos) {
        this.idGrupos = idGrupos;
    }

    public Integer getIdEstado() {
        return idEstado;
    }
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}


