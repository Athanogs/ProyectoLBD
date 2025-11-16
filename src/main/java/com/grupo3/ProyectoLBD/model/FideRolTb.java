package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_ROL_TB")
public class FideRolTb {

    @Id
    @Column(name = "ID_ROL")
    private Integer idRol;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public Integer getIdRol() { return idRol; }
    public void setIdRol(Integer idRol) { this.idRol = idRol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}