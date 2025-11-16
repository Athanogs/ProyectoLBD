package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_DISTRITO_TB")
public class FideDistritoTb {

    @Id
    @Column(name = "ID_DISTRITO")
    private Integer idDistrito;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public Integer getIdDistrito() { return idDistrito; }
    public void setIdDistrito(Integer idDistrito) { this.idDistrito = idDistrito; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}