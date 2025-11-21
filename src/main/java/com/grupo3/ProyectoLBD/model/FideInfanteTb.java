package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_INFANTE_TB")
public class FideInfanteTb {

    @Id
    @Column(name = "CEDULA_INFANTE")
    private String cedulaInfante;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "FECHA_NACIMIENTO")
    private java.sql.Date fechaNacimiento;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    // ============================
    // Getters / Setters
    // ============================
    public String getCedulaInfante() {
        return cedulaInfante;
    }

    public void setCedulaInfante(String cedulaInfante) {
        this.cedulaInfante = cedulaInfante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public java.sql.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}