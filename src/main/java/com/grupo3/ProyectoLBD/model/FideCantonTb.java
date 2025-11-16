package com.grupo3.ProyectoLBD.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIDE_CANTON_TB")
public class FideCantonTb {

    @Id
    @Column(name = "ID_CANTON")
    private Integer idCanton;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    public Integer getIdCanton() { return idCanton; }
    public void setIdCanton(Integer idCanton) { this.idCanton = idCanton; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
}