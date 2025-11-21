package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FIDE_EXPERIENCIA_LABORAL_V")
@IdClass(ExperienciaLaboralViewId.class)
public class ExperienciaLaboralView {

    @Id
    @Column(name = "CEDULA")
    private Long cedula;

    @Id
    @Column(name = "ID_EMPRESA")
    private Integer idEmpresa;

    @Id
    @Column(name = "ID_CARGO")
    private Integer idCargo;

    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "CARGO")
    private String cargo;

    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FINAL")
    private LocalDate fechaFinal;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public ExperienciaLaboralView() {
    }

    // Getters y setters
    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}